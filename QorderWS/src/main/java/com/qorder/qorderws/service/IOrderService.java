package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.model.order.EOrderStatus;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface IOrderService {
    
	long submitOrder(long businessId, @NotNull OrderDTO order);

	Collection<OrderViewDTO> fetchOrdersByBusinessID(long businessId);

	Collection<OrderViewDTO> fetchOrdersByStatus(long businessId, @NotNull EOrderStatus orderStatus);

	void changeOrderStatus(long orderId, @NotNull EOrderStatus orderStatus);

	OrderViewDTO fetchOrderById(long orderId);
	
}