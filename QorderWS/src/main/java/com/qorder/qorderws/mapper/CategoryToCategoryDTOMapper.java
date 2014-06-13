package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryToCategoryDTOMapper implements IMapper<Category, CategoryDTO> {

	@Override
	public CategoryDTO map(Category source, CategoryDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		return target;
	}

}
