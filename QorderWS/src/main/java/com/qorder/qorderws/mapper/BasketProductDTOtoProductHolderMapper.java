package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.product.BasketProductDTO;
import com.qorder.qorderws.model.order.ProductHolder;
import com.qorder.qorderws.model.product.Product;

public class BasketProductDTOtoProductHolderMapper implements
		IMapper<BasketProductDTO, ProductHolder> {

	@Override
	public ProductHolder map(BasketProductDTO source, ProductHolder target) {
		Product product = new Product();
		product.setId(source.getProductId());
		product.setName(source.getName());
		product.setPrice(source.getPrice());
		target.setProduct(product);
		
		target.setQuantity(source.getQuantity());
		target.setSelectedAttributes(source.getAttributes());
		target.setNotes(source.getNotes());
		return target;
	}
}
