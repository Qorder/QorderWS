package com.qorder.qorderws.service;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryService {
	
	boolean createCategory(long businessId, Category category) throws BusinessDoesNotExistException;
	
	//TODO: throws CategoryNotFoundException
	
	Category fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException;

}
