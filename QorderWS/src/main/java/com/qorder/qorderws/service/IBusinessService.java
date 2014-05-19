package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.ABusiness;

public interface IBusinessService {
	
	void createBusiness(BusinessDTO businessDTO);
	
	ABusiness fetchBusinessByID(Long businessId) throws ResourceNotFoundException;
	
}
