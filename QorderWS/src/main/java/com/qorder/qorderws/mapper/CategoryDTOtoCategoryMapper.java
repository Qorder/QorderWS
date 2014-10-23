package com.qorder.qorderws.mapper;

import com.google.common.base.Strings;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryDTOtoCategoryMapper implements IMapper<CategoryDTO, Category> {

	@Override
	public Category map(CategoryDTO source, Category target) {
		if(!Strings.isNullOrEmpty(source.getId())) {
			target.setId(Long.valueOf(source.getId()));	
		}
		target.setName(source.getName());
		
		return target;
	}

}
