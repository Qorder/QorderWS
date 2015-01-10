package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.model.product.Product;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

public class ProductToDetailedProductDTOMapper implements IMapper<Product, DetailedProductDTO>{

	@Override
	public DetailedProductDTO map(@NotNull Product source, @NotNull DetailedProductDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setPrice(source.getPrice().toPlainString());
		
		String details = StringUtils.collectionToDelimitedString(source.getDetails(), "-");
		target.setDetails(details);
		target.setDescription(source.getDescription());
		
		return target;
	}
}
