package com.qorder.qorderws.service;

import com.qorder.qorderws.dao.MenuDAOMock;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuService implements IMenuService {

	private IMenuDAO menuDAO = new MenuDAOMock();
	
	@Override
	public Menu fetchProxyMenuById(long businessId) {
		Menu menu = new Menu();
		menu.setProdTypeList(menuDAO.getProxyCategoryById(businessId));
		return menu;
	}

	@Override
	public Menu fetchMenuById(long businessID) {
		return null;
	}
}
