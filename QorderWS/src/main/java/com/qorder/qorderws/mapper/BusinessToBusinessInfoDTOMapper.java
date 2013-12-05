package com.qorder.qorderws.mapper;

import java.util.Iterator;

import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;



public final class BusinessToBusinessInfoDTOMapper implements IMapper<Business, BusinessInfoDTO> {

	@Override
	public BusinessInfoDTO map(Business source, BusinessInfoDTO target) {
		target.setBusinessName(source.getName());
		Iterator<Category> categoryItr = source.getCategoryList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryInfoDTO categoryInfo = new CategoryToCategoryInfoMapper().map( categoryItr.next(), new CategoryInfoDTO() );
			target.addCategoryInfo(categoryInfo);
		}
		return target;
	}
	
}
