package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryDAO {
	
	boolean save(Category category);
	
	boolean update(Category category) throws CategoryDoesNotExistException;
	
	boolean delete(Category category) throws CategoryDoesNotExistException;
	
	Category findById(long categoryId) throws CategoryDoesNotExistException;
}
