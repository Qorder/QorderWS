package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.order.OrderInfoDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.model.order.EOrderStatus;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface IOrderService {
    
	long submitOrder(long businessId, @NotNull OrderDTO order);

	Collection<OrderInfoDTO> fetchOrdersByBusinessID(long businessId);

	Collection<OrderInfoDTO> fetchOrdersByStatus(long businessId, @NotNull EOrderStatus orderStatus);

	void changeOrderStatus(long orderId, @NotNull EOrderStatus orderStatus);

	OrderInfoDTO fetchOrderById(long orderId);

	Collection<ProductHolderDTO> fetchOrderedProducts(long orderId);
}