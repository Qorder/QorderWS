package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface IBusinessService {
	
	void createBusiness(BusinessDTO businessDTO);
	
	BusinessDTO fetchBusinessByID(Long businessId) throws ResourceNotFoundException;
	
	BusinessDTO[] fetchBusinessesByUser(Long userId) throws ResourceNotFoundException;
	
}
