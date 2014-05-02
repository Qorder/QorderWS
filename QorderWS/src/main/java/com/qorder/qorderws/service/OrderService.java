
package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dao.IOrderDAO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.mapper.OrderDTOtoOrderMapper;
import com.qorder.qorderws.mapper.OrderToOrderViewDTOMapper;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderService implements IOrderService {
	
	private IOrderDAO orderDAO;
	private IBusinessDAO businessDAO;
	
	@Override
	public OrderViewDTO submitOrder(long businessId, OrderDTO orderDTO) throws BusinessDoesNotExistException {
		Order order = new  OrderDTOtoOrderMapper().map(orderDTO, new Order());
		order.setBusiness(businessDAO.findById(businessId));
		orderDAO.save(order);
		return new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
	}

	@Transactional(readOnly = true)
	@Override
	public OrderViewDTO[] fetchOrdersByBusinessID(long businessId) throws BusinessDoesNotExistException {
		List<Order> orderList = orderDAO.fetchOrdersForBusiness(businessId);
		List<OrderViewDTO> businessOrders = new ArrayList<OrderViewDTO>();
		for(Order order : orderList)
		{
			OrderViewDTO orderView = new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		}
		return businessOrders.toArray(new OrderViewDTO[businessOrders.size()]);
		
	}

	@Transactional(readOnly = true)
	@Override
	public OrderViewDTO[] fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws BusinessDoesNotExistException {
		
		List<Order> orderList = orderDAO.fetchOrdersByStatus(businessId, orderStatus);
		List<OrderViewDTO> businessOrders = new ArrayList<OrderViewDTO>();
		for(Order order : orderList)
		{
			OrderViewDTO orderView = new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		}
		return businessOrders.toArray(new OrderViewDTO[businessOrders.size()]);
	}

	@Override
	public void changeOrderStatus(Long orderId, EOrderStatus orderStatus) throws OrderDoesNotExistException {
		Order order = orderDAO.findById(orderId);
		order.setStatus(orderStatus);
		orderDAO.save(order);
	}
	
	@Override
	public OrderViewDTO fetchOrderById(Long orderId) throws OrderDoesNotExistException {
		Order order = orderDAO.findById(orderId);
		return new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
	}

	
	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}


	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}


	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}


	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}
	
}
