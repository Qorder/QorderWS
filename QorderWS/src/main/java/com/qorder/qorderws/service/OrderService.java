
package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.OrderDTOtoOrderMapper;
import com.qorder.qorderws.mapper.OrderToOrderViewDTOMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.repository.IBusinessRepository;
import com.qorder.qorderws.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class OrderService implements IOrderService {

	private IOrderRepository orderRepository;
	private IBusinessRepository businessRepository;

	@Autowired
	public OrderService(IOrderRepository orderRepository, IBusinessRepository businessRepository) {
		this.orderRepository = orderRepository;
		this.businessRepository = businessRepository;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long submitOrder(long businessId, OrderDTO orderDTO) throws ResourceNotFoundException {
		Order order = new  OrderDTOtoOrderMapper().map(orderDTO, new Order());
		order.setBusiness(businessRepository.findOne(businessId));
		orderRepository.save(order);
		return order.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderViewDTO> fetchOrdersByBusinessID(long businessId) throws ResourceNotFoundException {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByBusiness(business);
		List<OrderViewDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderViewDTO orderView = new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
		
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderViewDTO> fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws ResourceNotFoundException {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByStatus(business, orderStatus);
		List<OrderViewDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderViewDTO orderView = new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
	}

	@Transactional(readOnly = false)
	@Override
	public void changeOrderStatus(long orderId, EOrderStatus orderStatus) throws ResourceNotFoundException {
		Order order = orderRepository.findOne(orderId);
		order.setStatus(orderStatus);
		orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	@Override
	public OrderViewDTO fetchOrderById(long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findOne(orderId);
		return new OrderToOrderViewDTOMapper().map(order, new OrderViewDTO());
	}
	
}
