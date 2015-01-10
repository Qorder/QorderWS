package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

import javax.validation.constraints.NotNull;

public class CategoryToCategoryDTOMapper implements IMapper<Category, CategoryDTO> {

	@Override
	public CategoryDTO map(@NotNull Category source, @NotNull CategoryDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		
		return target;
	}

}
