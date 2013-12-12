package com.qorder.qorderws.mapper;

import org.springframework.util.StringUtils;

import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.model.product.Product;

public class ProductToDetailedProductDTOMapper implements IMapper<Product, DetailedProductDTO>{

	@Override
	public DetailedProductDTO map(Product source, DetailedProductDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPrice(source.getPrice());
		String details = StringUtils.collectionToDelimitedString(source.getDetails(), "-");
		target.setDetails(details);
		
		return target;
	}
}
