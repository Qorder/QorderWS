package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;

	//when the test are finished we should use categoryDAO instead of BusinessDoesNotExistException and businessDAO.
	@Override
	public Category fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException {
		return categoryDAO.findById(categoryId);	
	}


	@Override
	public boolean createCategory(long businessId, Category category) throws BusinessDoesNotExistException {
		return false;
	}
	
}
