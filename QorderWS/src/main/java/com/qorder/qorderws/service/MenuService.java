package com.qorder.qorderws.service;


import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IMenuDAO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.CategoryDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.MenuToMenuDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

public class MenuService implements IMenuService {

	private IMenuDAO menuDAO;
	private ICategoryDAO categoryDAO;
	
	@Transactional(readOnly = true)
	@Override
	public MenuDTO fetchMenuById(long menuId) throws ResourceNotFoundException {
		Menu menu = menuDAO.findById(menuId);
		return new MenuToMenuDTOMapper().map(menu, new MenuDTO());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long addCategory(long menuID, CategoryDTO categoryDTO) {
		Category category = new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category());
		
		Menu menu = menuDAO.findById(menuID);
		menu.addCategory(category);
		
		categoryDAO.save(category);
		menuDAO.update(menu);
		
		return category.getId();
	}
	

	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	
}
