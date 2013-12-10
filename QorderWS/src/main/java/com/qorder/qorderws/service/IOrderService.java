package com.qorder.qorderws.service;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.order.Order;

public interface IOrderService {
	
	boolean createOrder(long businessId, Order order) throws BusinessDoesNotExistException;

}