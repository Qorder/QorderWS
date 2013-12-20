package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.dto.DetailedCategoryDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryService {
	
	void createCategory(long businessId, Category category) throws BusinessDoesNotExistException;
	
	//TODO: throws CategoryNotFoundException
	
	DetailedCategoryDTO fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException;

}
