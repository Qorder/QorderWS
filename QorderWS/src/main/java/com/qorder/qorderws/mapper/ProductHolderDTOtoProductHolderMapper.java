package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.order.BasketProductDTO;
import com.qorder.qorderws.model.order.ProductHolder;
import com.qorder.qorderws.model.product.Product;

public class ProductHolderDTOtoProductHolderMapper implements IMapper<BasketProductDTO, ProductHolder> {

	@Override
	public ProductHolder map(BasketProductDTO source, ProductHolder target) {
		target.setNotes(source.getNotes());
		Product product = new Product();
		product.setId(source.getProductId());
		target.setProduct(product);
		return target;
	}

}
