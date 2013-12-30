package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;


@Transactional
public class BusinessService implements IBusinessService {

	private IBusinessDAO businessDAO;

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
	
	@Override
	public Business fetchBusinessById(long businessId) throws BusinessDoesNotExistException {
		return businessDAO.findById(businessId);
	}

	@Override
	public void createBusiness(Business business) {
		businessDAO.save(business);
	}

}
