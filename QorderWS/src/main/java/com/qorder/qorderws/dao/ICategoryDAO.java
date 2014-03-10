package com.qorder.qorderws.dao;

import java.util.List;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryDAO {
	
	boolean save(Category category);
	
	boolean update(Category category) throws CategoryDoesNotExistException;
	
	boolean delete(Category category) throws CategoryDoesNotExistException;
	
	List<Category> fetchCategoriesForBusiness(long businessId) throws BusinessDoesNotExistException;
	
	Category findById(long categoryId) throws CategoryDoesNotExistException;
}
