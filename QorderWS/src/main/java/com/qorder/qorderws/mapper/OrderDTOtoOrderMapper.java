package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.OrderDTO;
import com.qorder.qorderws.model.order.Order;

public class OrderDTOtoOrderMapper implements IMapper<OrderDTO, Order> {

	//FIXME: map the order
	@Override
	public Order map(OrderDTO source, Order target) {
		return new Order();
	}
}
