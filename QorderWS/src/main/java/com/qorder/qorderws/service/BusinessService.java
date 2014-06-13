package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.BusinessDTOtoBusinessMapper;
import com.qorder.qorderws.mapper.BusinessToBusinessDTOMapper;
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

	@Transactional(readOnly=true)
	@Override
	public BusinessDTO fetchBusinessByID(Long businessId) throws ResourceNotFoundException {
		ABusiness business = businessDAO.findById(businessId);
		return new BusinessToBusinessDTOMapper().map(business, new BusinessDTO());
	}

	@Transactional(readOnly=true)
	@Override
	public BusinessDTO[] fetchBusinessesByUser(Long userId) throws ResourceNotFoundException {
		List<ABusiness> businessList = businessDAO.fetchUserBusinesses(userId);
		List<BusinessDTO> userBusinesses = new ArrayList<BusinessDTO>();
		BusinessToBusinessDTOMapper mapper = new BusinessToBusinessDTOMapper();
		businessList.forEach((business)-> {
			userBusinesses.add(mapper.map(business, new BusinessDTO()));
		});
		return userBusinesses.toArray(new BusinessDTO[userBusinesses.size()]);
	}

}
