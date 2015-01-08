package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.order.EOrderStatus;

import java.util.Collection;

public interface IOrderService {
    
	long submitOrder(long businessId, OrderDTO order) throws ResourceNotFoundException;

	Collection<OrderViewDTO> fetchOrdersByBusinessID(long businessId) throws ResourceNotFoundException;

	Collection<OrderViewDTO> fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws ResourceNotFoundException;

	void changeOrderStatus(long orderId, EOrderStatus orderStatus) throws ResourceNotFoundException;

	OrderViewDTO fetchOrderById(long orderId) throws ResourceNotFoundException;
	
}