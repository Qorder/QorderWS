package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.DetailedCategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryToDtoMapper implements IMapper<Category, DetailedCategoryDTO> {

	@Override
	public DetailedCategoryDTO map(Category source, DetailedCategoryDTO target) {
		target.setName(source.getName());
		
		return target;
	}

}
