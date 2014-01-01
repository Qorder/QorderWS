package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.BusinessOrdersDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.EOrderStatus;

public interface IOrderService {
    
	void submitOrder(long businessId, OrderDTO order) throws BusinessDoesNotExistException;
    
    BusinessOrdersDTO fetchOrdersByBusinessID(long businessId) throws BusinessDoesNotExistException;

	BusinessOrdersDTO fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws BusinessDoesNotExistException;

	void changeOrderStatus(Long orderId, EOrderStatus orderStatus) throws OrderDoesNotExistException;

}