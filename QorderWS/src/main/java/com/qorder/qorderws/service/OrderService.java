
package com.qorder.qorderws.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dao.IOrderDAO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.dto.order.PendingOrdersDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.mapper.OrderDTOtoOrderMapper;
import com.qorder.qorderws.mapper.OrderToOrderViewDTOMapper;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderService implements IOrderService {
	
	private IBusinessDAO businessDAO;
	private IOrderDAO orderDAO;
	
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

	@Override
	public void submitOrder(long businessId, OrderDTO orderDTO) throws BusinessDoesNotExistException {
		Order order = new OrderDTOtoOrderMapper().map(orderDTO, new Order());
		order.setBusiness(businessDAO.findById(businessId));
		orderDAO.save(order);
	}


	@Override
	public PendingOrdersDTO fetchOrdersFromBusinessID(long businessId) throws BusinessDoesNotExistException {
		List<Order> orderList = orderDAO.fetchOrderForBusiness(businessId);
		
		PendingOrdersDTO businessOrders = new PendingOrdersDTO();
		for(Order order : orderList)
		{
			OrderViewDTO orderView = new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
			businessOrders.addOrderViewDTO(orderView);
			orderDAO.delete(order);
		}
		return businessOrders;
	}
}
