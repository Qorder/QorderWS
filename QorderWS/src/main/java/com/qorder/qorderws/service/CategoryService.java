package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IMenuDAO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.MenuDoesNotExistException;
import com.qorder.qorderws.mapper.CategoryDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.CategoryToDetailedCategoryDtoMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	private IMenuDAO menuDAO;

	@Transactional(readOnly = true)
	@Override
	public DetailedCategoryDTO fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException {
		Category fetchedCategory = categoryDAO.findById(categoryId);
		return new CategoryToDetailedCategoryDtoMapper().map(fetchedCategory, new DetailedCategoryDTO());
	}

	@Override
	public void createCategory(long menuId, CategoryDTO categoryDTO) throws MenuDoesNotExistException {
		Menu menu = menuDAO.findById(menuId); 
		
		Category category = new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category());
		
		menu.getCategoryList().add(category);
		menuDAO.update(menu);
	}


	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}


	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
}
