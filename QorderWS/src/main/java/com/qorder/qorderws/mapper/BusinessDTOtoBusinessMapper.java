package com.qorder.qorderws.mapper;

import com.google.common.base.Strings;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.model.business.Business;

public class BusinessDTOtoBusinessMapper implements IMapper<BusinessDTO, Business> {

	@Override
	public Business map(BusinessDTO source, Business target) {
		if( !Strings.isNullOrEmpty(source.getId()) )
		{
			target.setId(Long.valueOf(source.getId()));
		}
		target.setName(source.getName());
		return target;
	}
	
	

}
