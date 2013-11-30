package com.qorder.qorderws.service;

import java.util.List;

import com.qorder.qorderws.dao.MenuDAOMock;
import com.qorder.qorderws.model.category.ICategory;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuService implements IMenuService {

	private IMenuDAO menuDAO = new MenuDAOMock();

	@Override
	public Menu fetchMenuById(long businessId) {
		Menu menu = new Menu();
		List<ICategory> categoryList = menuDAO.getCategoryListById(businessId);
		//TODO: find a way to create a DTO for category
		return menu;
	}
}
