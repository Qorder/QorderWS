package com.qorder.qorderws.service;

import java.util.Iterator;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryService implements ICategoryService {

	private IBusinessDAO businessDAO;

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	@Override
	public boolean createCategory(long businessId, Category category) throws BusinessDoesNotExistException {
		Business business = businessDAO.findById(businessId);
		business.getCategoryList().add(category);
		return businessDAO.update(business);
	}

	//when the test are finished we should use categoryDAO instead of BusinessDoesNotExistException and businessDAO.
	@Override
	public Category getCategoryByID(long categoryId) throws CategoryDoesNotExistException, BusinessDoesNotExistException {
		//TODO: categoryDAO:
		Business business = businessDAO.findById(0);
		Iterator<Category> categoryItr = business.getCategoryList().iterator();
		while(categoryItr.hasNext())
		{
			Category category = categoryItr.next();
			if(category.getId() == categoryId)
			{
				return category;
			}
		}
		throw new CategoryDoesNotExistException();
	}
	
}
