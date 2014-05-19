package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.model.business.ABusiness;

public class BusinessToBusinessDTOMapper implements IMapper<ABusiness, BusinessDTO> {

	@Override
	public BusinessDTO map(ABusiness source, BusinessDTO target) {
		if(source.getId() != null)
		{
			target.setId(source.getId());
		}
		target.setName(source.getName());
		target.setMenu(new MenuToMenuDTOMapper().map(source.getMenu(), new MenuDTO()));
		return target;
	}

}
