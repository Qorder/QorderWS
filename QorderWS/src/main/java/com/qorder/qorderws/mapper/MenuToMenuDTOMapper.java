package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;



public final class MenuToMenuDTOMapper implements IMapper<Menu, MenuDTO> {

	@Override
	public MenuDTO map(Menu source, MenuDTO target) {
		if(source.getId() != null)
		{
			target.setId(source.getId());
		}
		for(Category category : source.getCategoryList())
		{
			CategoryDTO categoryDTO = new CategoryToCategoryDTOMapper().map( category, new CategoryDTO() );
			target.addCategoryInfo(categoryDTO);
		}
		return target;
	}
	
}
