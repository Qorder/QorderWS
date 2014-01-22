package com.qorder.qorderws.service;


import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IMenuDAO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.MenuDoesNotExistException;
import com.qorder.qorderws.mapper.MenuToMenuDTOMapper;
import com.qorder.qorderws.model.menu.Menu;

@Transactional
public class MenuService implements IMenuService {

	private IMenuDAO menuDAO;
	
	@Override
	public MenuDTO fetchMenuById(Long menuId) throws MenuDoesNotExistException {
		Menu menu = menuDAO.findById(menuId);
		return new MenuToMenuDTOMapper().map(menu, new MenuDTO());
	}

	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
}
