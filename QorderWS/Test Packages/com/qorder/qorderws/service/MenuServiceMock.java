package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.ProductMenu;

public class MenuServiceMock implements IMenuService {

	
	@Override
	public ProductMenu fetchMenuById(Long businessId) {
		
		return new ProductMenu();
	}
}
