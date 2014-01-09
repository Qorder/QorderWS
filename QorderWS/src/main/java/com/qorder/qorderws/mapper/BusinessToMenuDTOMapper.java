package com.qorder.qorderws.mapper;

import java.util.Iterator;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;



public final class BusinessToMenuDTOMapper implements IMapper<Business, MenuDTO> {

	@Override
	public MenuDTO map(Business source, MenuDTO target) {
		target.setBusinessName(source.getName());
		
		Iterator<Category> categoryItr = source.getMenu().getCategoryList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryDTO categoryInfo = new CategoryToCategoryDTOMapper().map( categoryItr.next(), new CategoryDTO() );
			target.addCategoryInfo(categoryInfo);
		}
		return target;
	}
	
}
