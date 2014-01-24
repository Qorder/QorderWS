package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.EOrderStatus;

public interface IOrderService {
    
	OrderViewDTO submitOrder(long businessId, OrderDTO order) throws BusinessDoesNotExistException;
    
	OrderViewDTO[] fetchOrdersByBusinessID(long businessId) throws BusinessDoesNotExistException;

	OrderViewDTO[] fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws BusinessDoesNotExistException;

	void changeOrderStatus(Long orderId, EOrderStatus orderStatus) throws OrderDoesNotExistException;

	OrderViewDTO fetchOrderById(Long orderId) throws OrderDoesNotExistException;
	
}