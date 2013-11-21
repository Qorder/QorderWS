package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.IProductMenu;
import com.qorder.qorderws.model.menu.ProductMenu;

public class MenuServiceMock implements IMenuService {

	
	@Override
	public IProductMenu fetchMenuById(Long businessId) {
		
		return new ProductMenu((long) businessId);
	}
}
