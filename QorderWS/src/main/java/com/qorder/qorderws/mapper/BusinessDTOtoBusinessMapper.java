package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.model.business.Business;

import javax.validation.constraints.NotNull;

public class BusinessDTOtoBusinessMapper implements IMapper<BusinessDTO, Business> {

	@Override
	public Business map(@NotNull BusinessDTO source, @NotNull Business target) {
		target.setName(source.getName());
		return target;
	}
	
	

}
