
package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dao.IOrderDAO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.OrderDTOtoOrderMapper;
import com.qorder.qorderws.mapper.OrderToOrderViewDTOMapper;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderService implements IOrderService {
	
	private IOrderDAO orderDAO;
	private IBusinessDAO businessDAO;
	
	@Override
	public long submitOrder(long businessId, OrderDTO orderDTO) throws ResourceNotFoundException {
		Order order = new  OrderDTOtoOrderMapper().map(orderDTO, new Order());
		order.setBusiness(businessDAO.findById(businessId));
		orderDAO.save(order);
		return order.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public OrderViewDTO[] fetchOrdersByBusinessID(long businessId) throws ResourceNotFoundException {
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
	public OrderViewDTO[] fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws ResourceNotFoundException {
		
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
	public void changeOrderStatus(long orderId, EOrderStatus orderStatus) throws ResourceNotFoundException {
		Order order = orderDAO.findById(orderId);
		order.setStatus(orderStatus);
		orderDAO.save(order);
	}
	
	@Override
	public OrderViewDTO fetchOrderById(long orderId) throws ResourceNotFoundException {
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
