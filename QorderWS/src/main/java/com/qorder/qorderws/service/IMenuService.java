package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.IProductMenu;

public interface IMenuService {
	
	IProductMenu fetchMenuById(Long businessID);

}
