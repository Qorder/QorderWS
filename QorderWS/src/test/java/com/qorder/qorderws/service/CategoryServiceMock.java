package com.qorder.qorderws.service;

import java.util.Iterator;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

public class CategoryServiceMock implements ICategoryService {
	
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
	public Category fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException {
		//TODO: categoryDAO:
		Business business;
		try 
		{
			business = businessDAO.findById(0);
			Iterator<Category> categoryItr = business.getCategoryList().iterator();
			while(categoryItr.hasNext())
			{
				Category category = categoryItr.next();
				if(category.getId() == categoryId)
				{
					return category;
				}
			}
		} 
		catch (BusinessDoesNotExistException e) {
			e.printStackTrace();
		}
		throw new CategoryDoesNotExistException();
	}

}
