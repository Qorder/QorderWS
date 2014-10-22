package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.model.business.Business;

public class BusinessToBusinessDTOMapper implements IMapper<Business, BusinessDTO> {

	@Override
	public BusinessDTO map(Business source, BusinessDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setMenuId(source.getMenu().getId());
		return target;
	}

}
