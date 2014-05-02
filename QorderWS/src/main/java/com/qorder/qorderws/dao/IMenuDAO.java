package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.MenuDoesNotExistException;
import com.qorder.qorderws.model.menu.Menu;

public interface IMenuDAO {
	
	void save(Menu menu);
	
	void update(Menu menu) throws MenuDoesNotExistException;
	
	Menu delete(Long menuId) throws MenuDoesNotExistException;
	
	Menu findById(long menuId) throws MenuDoesNotExistException;
}
