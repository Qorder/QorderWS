package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.model.business.ABusiness;

public class BusinessToBusinessDTOMapper implements IMapper<ABusiness, BusinessDTO> {

	@Override
	public BusinessDTO map(ABusiness source, BusinessDTO target) {
		target.setId(source.getId());
		target.setName(source.getName());
		target.setMenuId(source.getMenu().getId());
		return target;
	}

}
