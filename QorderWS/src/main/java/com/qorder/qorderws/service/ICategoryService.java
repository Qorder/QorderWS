package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.MenuDoesNotExistException;

public interface ICategoryService {
	
	void createCategory(long menuId, CategoryDTO categoryDTO) throws MenuDoesNotExistException;
	
	DetailedCategoryDTO fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException;
	
}
