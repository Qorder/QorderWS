package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.model.business.Business;

public class BusinessService implements IBusinessService {

	private IBusinessDAO businessDAO;
	
	@Transactional
	@Override
	public Business fetchBusinessById(long businessId) {
		return businessDAO.findById(businessId);
	}

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
}
