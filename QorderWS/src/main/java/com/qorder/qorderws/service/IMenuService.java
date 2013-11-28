package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.Menu;

public interface IMenuService {
	
	Menu fetchMenuById(Long businessID);

}
