package com.qorder.qorderws.service;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

public interface IBusinessService {
	
	Business fetchBusinessById(long businessId) throws BusinessDoesNotExistException;
}
