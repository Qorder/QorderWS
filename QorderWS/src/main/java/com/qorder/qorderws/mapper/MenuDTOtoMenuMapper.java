package com.qorder.qorderws.mapper;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

public class MenuDTOtoMenuMapper implements IMapper<MenuDTO, Menu> {

	@Override
	public Menu map(MenuDTO source, Menu target) {
		if(source.getId() != null)
		{
			target.setId(source.getId());
		}
		List<Category> categoryList = new ArrayList<Category>();
		for(CategoryDTO categoryDTO : source.getCategoryInfoList()) {
			categoryList.add(new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category()));
		}
		target.setCategoryList(categoryList);

		return target;
	}

}
