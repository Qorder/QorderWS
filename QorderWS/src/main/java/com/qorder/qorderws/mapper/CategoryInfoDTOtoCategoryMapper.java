package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryInfoDTOtoCategoryMapper implements IMapper<CategoryInfoDTO, Category> {

	@Override
	public Category map(CategoryInfoDTO source, Category target) {
		target.setId(source.getId());
		target.setName(source.getName());
		return target;
	}

}
