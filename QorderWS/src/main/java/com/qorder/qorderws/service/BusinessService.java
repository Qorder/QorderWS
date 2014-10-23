package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.BusinessDTOtoBusinessMapper;
import com.qorder.qorderws.mapper.BusinessToBusinessDTOMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.menu.Menu;

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
	public long createBusiness(BusinessDTO businessDTO) {
		Business business = new BusinessDTOtoBusinessMapper().map(businessDTO, new Business());
		business.setMenu(new Menu());
		businessDAO.save(business);
		
		return business.getId();
	}
	

	@Transactional(readOnly=true)
	@Override
	public BusinessDTO fetchBusinessByID(long businessId) throws ResourceNotFoundException {
		Business business = businessDAO.findById(businessId);
		return new BusinessToBusinessDTOMapper().map(business, new BusinessDTO());
	}

	@Transactional(readOnly=true)
	@Override
	public BusinessDTO[] fetchBusinessesByUser(long userId) throws ResourceNotFoundException {
		List<Business> businessList = businessDAO.fetchUserBusinesses(userId);
		List<BusinessDTO> userBusinesses = new ArrayList<BusinessDTO>();
		
		BusinessToBusinessDTOMapper mapper = new BusinessToBusinessDTOMapper();
		businessList.forEach((business)-> {
			userBusinesses.add(mapper.map(business, new BusinessDTO()));
		});
		return userBusinesses.toArray(new BusinessDTO[userBusinesses.size()]);
	}

}
