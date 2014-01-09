package com.qorder.qorderws.mapper;

import java.util.Arrays;
import java.util.List;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.model.product.Product;

public class DetailedProductDTOtoProductMapper implements IMapper<DetailedProductDTO, Product> {

	@Override
	public Product map(DetailedProductDTO source, Product target) {
		target.setName(source.getName());
		if(source.getDetails() != null)
		{
			List<String> descriptions = Arrays.asList(source.getDetails().split("-"));
			target.setDescriptions(descriptions);
		}
		target.setPrice(source.getPrice());
		return target;
	}

}
