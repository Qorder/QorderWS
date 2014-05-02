package com.qorder.qorderws.dao;

import java.util.List;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

public interface IOrderDAO {
	
	Order save(Order order);

	boolean update(Order order) throws OrderDoesNotExistException;

	boolean delete(Order order);
	
	Order findById(long orderId) throws OrderDoesNotExistException;
	
	public List<Order> fetchOrdersForBusiness(long businessId) throws BusinessDoesNotExistException;

	List<Order> fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws BusinessDoesNotExistException;
}