package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class MenuDTOtoMenuMapper implements IMapper<MenuDTO, Menu> {

	@Override
	public Menu map(@NotNull MenuDTO source, @NotNull Menu target) {
		List<Category> categoryList = new ArrayList<>();
		for(CategoryDTO categoryDTO : source.getCategoryInfoList()) {
			categoryList.add(new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category()));
		}
		target.setCategoryList(categoryList);

		return target;
	}

}
