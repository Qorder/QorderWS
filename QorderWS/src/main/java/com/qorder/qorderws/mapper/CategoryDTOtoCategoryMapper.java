package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryDTOtoCategoryMapper implements IMapper<CategoryDTO, Category> {

	@Override
	public Category map(CategoryDTO source, Category target) {
		target.setName(source.getName());
		return target;
	}

}
