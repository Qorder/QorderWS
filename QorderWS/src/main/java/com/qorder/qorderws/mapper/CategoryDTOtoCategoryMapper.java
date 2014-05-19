package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryDTOtoCategoryMapper implements IMapper<CategoryDTO, Category> {

	@Override
	public Category map(CategoryDTO source, Category target) {
		target.setId(source.getId());
		target.setName(source.getName());
		
		return target;
	}

}
