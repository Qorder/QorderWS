package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.order.BasketProductDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.model.order.ProductHolder;

public class OrderDTOtoOrderMapper implements IMapper<OrderDTO, Order> {

	@Override
	public Order map(OrderDTO source, Order target) {
		target.setTableNumber(source.getTableNumber());
		for (BasketProductDTO productHolderDTO : source.getOrders()) {
			ProductHolder productHolder = new BasketProductDTOtoProductHolderMapper()
					.map(productHolderDTO, new ProductHolder());
			target.add(productHolder);
		}
		return target;
	}
}
