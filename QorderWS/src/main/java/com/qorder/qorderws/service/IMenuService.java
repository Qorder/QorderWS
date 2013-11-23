package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.ProductMenu;

public interface IMenuService {
	
	ProductMenu fetchMenuById(Long businessID);

}
