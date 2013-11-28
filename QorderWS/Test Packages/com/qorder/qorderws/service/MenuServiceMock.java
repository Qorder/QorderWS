package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.Menu;

public class MenuServiceMock implements IMenuService {

	
	@Override
	public Menu fetchMenuById(Long businessId) {
		
		return new Menu();
	}
}
