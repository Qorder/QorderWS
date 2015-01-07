package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.order.OrderViewDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.model.order.Order;
import com.qorder.qorderws.model.order.ProductHolder;

public class OrderToOrderViewDTOMapper implements IMapper<Order, OrderViewDTO> {

	@Override
	public OrderViewDTO map(Order source, OrderViewDTO target) {
		target.setTableNumber(source.getTableNumber());
		target.setDateTime(source.getDateTime());
		target.setTotalPrice(source.getTotalPrice().toString());
		target.setStatus(source.getStatus().toString());
		for(ProductHolder productHolder : source.getOrderList()) {
			ProductHolderDTO productHolderDTO = new ProductHolderToProductHolderDTOMapper().map(productHolder, new ProductHolderDTO());
			target.add(productHolderDTO);
		}
		return target;
	}
	
	

}
