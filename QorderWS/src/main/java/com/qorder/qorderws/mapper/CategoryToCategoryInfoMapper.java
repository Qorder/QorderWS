package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryToCategoryInfoMapper implements IMapper<Category, CategoryDTO> {

	@Override
	public CategoryDTO map(Category source, CategoryDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		
		return target;
	}

}
