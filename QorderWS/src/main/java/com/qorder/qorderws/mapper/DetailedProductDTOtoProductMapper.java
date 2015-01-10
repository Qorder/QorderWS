package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.model.product.Product;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class DetailedProductDTOtoProductMapper implements IMapper<DetailedProductDTO, Product> {

	@Override
	public Product map(@NotNull DetailedProductDTO source, @NotNull Product target) {
		target.setName(source.getName());
		if(source.getDetails() != null)
		{
			List<String> details = Arrays.asList(source.getDetails().split("-"));
			target.setDetails(details);
		}
		target.setDescription(source.getDescription());
		target.setPrice(new BigDecimal(source.getPrice()));
		return target;
	}

}
