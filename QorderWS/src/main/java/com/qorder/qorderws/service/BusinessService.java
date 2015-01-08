package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.BusinessDTOtoBusinessMapper;
import com.qorder.qorderws.mapper.BusinessToBusinessDTOMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.repository.IBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class BusinessService implements IBusinessService {

	private final IBusinessRepository businessRepository;

	@Autowired
	public BusinessService(IBusinessRepository businessRepository) {
		this.businessRepository = businessRepository;
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long createBusiness(BusinessDTO businessDTO) {
		Business business = new BusinessDTOtoBusinessMapper().map(businessDTO, new Business());
		business.setMenu(new Menu());
		businessRepository.save(business);

		return business.getId();
	}
	

	@Transactional(readOnly = true)
	@Override
	public BusinessDTO fetchBusinessByID(long businessId) throws ResourceNotFoundException {
		Business business = businessRepository.findOne(businessId);
		return new BusinessToBusinessDTOMapper().map(business, new BusinessDTO());
	}

	//TODO: real implementation of this method
	@Transactional(readOnly = true)
	@Override
	public Collection<BusinessDTO> fetchBusinessesByUser(long userId) throws ResourceNotFoundException {
		List<Business> businessList = businessRepository.findAll();
		List<BusinessDTO> userBusinesses = new ArrayList<>();
		
		BusinessToBusinessDTOMapper mapper = new BusinessToBusinessDTOMapper();
		businessList.forEach((business)-> {
			userBusinesses.add(mapper.map(business, new BusinessDTO()));
		});
		return userBusinesses;
	}

}
