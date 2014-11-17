package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface IMenuService {
	
	MenuDTO fetchMenuById(long menuId) throws ResourceNotFoundException;

	long addCategory(long menuID, CategoryDTO categoryDTO);

}
