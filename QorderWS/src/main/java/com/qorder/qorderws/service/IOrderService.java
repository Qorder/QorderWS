package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.BusinessOrdersDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;

public interface IOrderService {
    
	void submitOrder(long businessId, OrderDTO order) throws BusinessDoesNotExistException;
    
    BusinessOrdersDTO fetchOrdersFromBusinessID(long businessId) throws BusinessDoesNotExistException;

}