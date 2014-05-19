package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.order.EOrderStatus;

public interface IOrderService {
    
	OrderViewDTO submitOrder(long businessId, OrderDTO order) throws ResourceNotFoundException;
    
	OrderViewDTO[] fetchOrdersByBusinessID(long businessId) throws ResourceNotFoundException;

	OrderViewDTO[] fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws ResourceNotFoundException;

	void changeOrderStatus(Long orderId, EOrderStatus orderStatus) throws ResourceNotFoundException;

	OrderViewDTO fetchOrderById(Long orderId) throws ResourceNotFoundException;
	
}