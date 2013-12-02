package com.qorder.qorderws.service;

import java.util.List;

import com.qorder.qorderws.dao.MenuDAOMock;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuService implements IMenuService {

	private IMenuDAO menuDAO = new MenuDAOMock();

	@Override
	public Menu fetchMenuById(long businessId) {
		Menu menu = new Menu();
		List<Category> categoryList = menuDAO.getCategoryListById(businessId);
		menu.setCategoryList(categoryList);
		return menu;
	}
}
