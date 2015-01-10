package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;

import javax.validation.constraints.NotNull;

public interface IMenuService {
	
	MenuDTO fetchMenuById(long menuId);

	long addCategory(long menuID, @NotNull CategoryDTO categoryDTO);

}
