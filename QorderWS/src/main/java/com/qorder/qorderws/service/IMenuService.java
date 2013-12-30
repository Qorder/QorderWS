package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;

public interface IMenuService {
	
	MenuDTO getMenuByBusinessId (long businessId) throws BusinessDoesNotExistException;

}
