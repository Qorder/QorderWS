package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface IMenuService {
	
	MenuDTO fetchMenuById(long menuId) throws ResourceNotFoundException;

}
