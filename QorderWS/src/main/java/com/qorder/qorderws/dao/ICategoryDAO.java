package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryDAO {
	
	Category fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException;
	
	boolean createCategory(long businessId, Category category);

}
