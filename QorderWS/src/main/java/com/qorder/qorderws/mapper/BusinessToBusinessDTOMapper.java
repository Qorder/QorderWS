package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.model.business.Business;

import javax.validation.constraints.NotNull;

public class BusinessToBusinessDTOMapper implements IMapper<Business, BusinessDTO> {

	@Override
	public BusinessDTO map(@NotNull Business source, @NotNull BusinessDTO target) {

		target.setName(source.getName());
		target.setMenuId(source.getMenu().getId());
		
		return target;
	}

}
