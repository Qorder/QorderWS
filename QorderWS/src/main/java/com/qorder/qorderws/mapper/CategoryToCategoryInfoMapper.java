package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.model.category.Category;

public class CategoryToCategoryInfoMapper implements IMapper<Category, CategoryInfoDTO> {

	@Override
	public CategoryInfoDTO map(Category source, CategoryInfoDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		return target;
	}

}
