package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;

import javax.validation.constraints.NotNull;

public class CategoryDTOtoCategoryMapper implements IMapper<CategoryDTO, Category> {

	@Override
	public Category map(@NotNull CategoryDTO source, @NotNull Category target) {
		target.setName(source.getName());
		return target;
	}

}
