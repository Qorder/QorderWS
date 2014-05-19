package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.BusinessDTOtoBusinessMapper;
import com.qorder.qorderws.model.business.ABusiness;
import com.qorder.qorderws.model.business.Business;


@Transactional
public class BusinessService implements IBusinessService {

	private IBusinessDAO businessDAO;

	@Override
	public void createBusiness(BusinessDTO businessDTO) {
		Business business = new BusinessDTOtoBusinessMapper().map(businessDTO, new Business());
		businessDAO.save(business);
	}
	

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}


	@Override
	public ABusiness fetchBusinessByID(Long businessId) throws ResourceNotFoundException {
		ABusiness business = businessDAO.findById(businessId);
		return business;
	}

}
