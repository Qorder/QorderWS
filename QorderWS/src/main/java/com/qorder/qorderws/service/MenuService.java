package com.qorder.qorderws.service;


import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.mapper.BusinessToMenuDTOMapper;
import com.qorder.qorderws.model.business.Business;

@Transactional
public class MenuService implements IMenuService {

	private IBusinessDAO businessDAO;
	
	@Transactional(readOnly = true)
	@Override
	public MenuDTO fetchMenuByBusinessId(long businessId) throws BusinessDoesNotExistException {
		Business business = businessDAO.findById(businessId);
		return new BusinessToMenuDTOMapper().map(business, new MenuDTO());
	}
	
	
	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

}
