package com.qorder.qorderws.service;

import com.qorder.qorderws.model.menu.Menu;

public interface IMenuService {
	
	Menu fetchProxyMenuById(long businessID);
	
	Menu fetchMenuById(long businessID);

}
