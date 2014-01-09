package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;

public interface ICategoryService {
	
	void createCategory(long businessId, CategoryDTO categoryDTO) throws BusinessDoesNotExistException;
	
	DetailedCategoryDTO fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException;
	
}
