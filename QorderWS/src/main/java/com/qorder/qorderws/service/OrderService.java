
package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.repository.IBusinessRepository;
import com.qorder.qorderws.repository.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class OrderService implements IOrderService {

	private final IOrderRepository orderRepository;
	private final IBusinessRepository businessRepository;

	private final IMapper mapper;

	@Autowired
	public OrderService(IOrderRepository orderRepository, IBusinessRepository businessRepository, IMapper mapper) {
		this.orderRepository = orderRepository;
		this.businessRepository = businessRepository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long submitOrder(long businessId, @NotNull OrderDTO orderDTO) {
		Order order = mapper.map(orderDTO, new Order());
		order.setBusiness(businessRepository.findOne(businessId));
		orderRepository.save(order);
		return order.getId();
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderViewDTO> fetchOrdersByBusinessID(long businessId) {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByBusiness(business);
		List<OrderViewDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderViewDTO orderView = mapper.map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
		
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<OrderViewDTO> fetchOrdersByStatus(long businessId, @NotNull EOrderStatus orderStatus) {
		Business business = businessRepository.findOne(businessId);
		List<Order> orders = orderRepository.findOrdersByStatus(business, orderStatus);
		List<OrderViewDTO> businessOrders = new ArrayList<>();

		orders.forEach((order) -> {
			OrderViewDTO orderView = mapper.map(order, new OrderViewDTO());
			businessOrders.add(orderView);
		});
		return businessOrders;
	}

	@Transactional(readOnly = false)
	@Override
	public void changeOrderStatus(long orderId, @NotNull EOrderStatus orderStatus) {
		Order order = orderRepository.findOne(orderId);
		order.setStatus(orderStatus);
		orderRepository.save(order);
	}
	
	@Transactional(readOnly = true)
	@Override
	public OrderViewDTO fetchOrderById(long orderId) {
		Order order = orderRepository.findOne(orderId);
		return Objects.nonNull(order) ? mapper.map(order, new OrderViewDTO()) : new OrderViewDTO();
	}
	
}
