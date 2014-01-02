package com.qorder.qorderws.mapper;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dto.order.BasketProductDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.model.order.ProductHolder;

@Transactional
public class OrderDTOtoOrderMapper implements IMapper<OrderDTO, Order> {

	@Override
	public Order map(OrderDTO source, Order target) {
		BigDecimal totalPrice = new BigDecimal(0);

		target.setTableNumber(source.getTableNumber());
		for (BasketProductDTO basketProductDTO : source.getOrders()) {
			ProductHolder productHolder = new BasketProductDTOtoProductHolderMapper()
					.map(basketProductDTO, new ProductHolder());
			
			target.add(productHolder);
			totalPrice = totalPrice.add(productHolder.getHoldingProductsPrice());
		}
		target.setTotalPrice(totalPrice);
		return target;
	}
}
