package com.qorder.qorderws.mapper;

import java.util.Iterator;

import com.qorder.qorderws.dto.DetailedCategoryDTO;
import com.qorder.qorderws.dto.ProductDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class CategoryToDtoMapper implements IMapper<Category, DetailedCategoryDTO> {

	@Override
	public DetailedCategoryDTO map(Category source, DetailedCategoryDTO target) {
		target.setName(source.getName());
		Iterator<Product> productItr = source.getProductList().iterator();
		while(productItr.hasNext())
		{
			target.addProductDTO(new ProductToDtoMapper().map(productItr.next(), new ProductDTO()));
		}
		return target;
	}

}
