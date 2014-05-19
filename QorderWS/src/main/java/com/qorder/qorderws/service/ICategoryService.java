package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface ICategoryService {
	
	void createCategory(long menuId, CategoryDTO categoryDTO) throws ResourceNotFoundException;
	
	DetailedCategoryDTO fetchCategoryByID(long categoryId) throws ResourceNotFoundException;
	
}
