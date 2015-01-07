package com.qorder.qorderws.repository;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

import java.util.List;

public interface IOrderDAO {

	Order save(Order order);

	void update(Order order) throws PersistanceLayerException;

	void delete(Order order) throws PersistanceLayerException;

	Order findById(long orderId) throws PersistanceLayerException, ResourceNotFoundException;

	public List<Order> fetchOrdersForBusiness(long businessId)
			throws PersistanceLayerException, ResourceNotFoundException;

	List<Order> fetchOrdersByStatus(long businessId, EOrderStatus orderStatus)
			throws PersistanceLayerException, ResourceNotFoundException;
}