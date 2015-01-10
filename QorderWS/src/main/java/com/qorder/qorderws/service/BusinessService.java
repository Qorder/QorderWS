package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.mapper.BusinessDTOtoBusinessMapper;
import com.qorder.qorderws.mapper.BusinessToBusinessDTOMapper;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.repository.IBusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

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
	public long createBusiness(@NotNull BusinessDTO businessDTO) {
		Business business = new BusinessDTOtoBusinessMapper().map(businessDTO, new Business());
		business = businessRepository.save(business);

		return business.getId();
	}
	

	@Transactional(readOnly = true)
	@Override
	public BusinessDTO fetchBusinessByID(long businessId) {
		Business business = businessRepository.findOne(businessId);
		return Objects.nonNull(business) ?
				new BusinessToBusinessDTOMapper().map(business, new BusinessDTO()) : new BusinessDTO();
	}

	//TODO: real implementation of this method
	@Transactional(readOnly = true)
	@Override
	public Collection<BusinessDTO> fetchBusinessesByUser(long userId) {
		List<Business> businessList = businessRepository.findAll();
		List<BusinessDTO> userBusinesses = new ArrayList<>();
		
		IMapper<Business, BusinessDTO> mapper = new BusinessToBusinessDTOMapper();
		businessList.forEach((business) -> {
			userBusinesses.add(mapper.map(business, new BusinessDTO()));
		});
		return userBusinesses;
	}

}
