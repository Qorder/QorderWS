package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface IBusinessService {
	
	long createBusiness(BusinessDTO businessDTO);
	
	BusinessDTO fetchBusinessByID(long businessId) throws ResourceNotFoundException;
	
	BusinessDTO[] fetchBusinessesByUser(long userId) throws ResourceNotFoundException;
	
}
