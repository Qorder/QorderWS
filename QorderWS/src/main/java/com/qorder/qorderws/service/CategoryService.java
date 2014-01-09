package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dto.category.CategoryDTO;
import com.qorder.qorderws.dto.category.DetailedCategoryDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.mapper.CategoryDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.CategoryToDetailedCategoryDtoMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	private IBusinessDAO businessDAO;

	@Transactional(readOnly = true)
	@Override
	public DetailedCategoryDTO fetchCategoryByID(long categoryId) throws CategoryDoesNotExistException {
		Category fetchedCategory = categoryDAO.findById(categoryId);
		return new CategoryToDetailedCategoryDtoMapper().map(fetchedCategory, new DetailedCategoryDTO());
	}

	@Override
	public void createCategory(long businessId, CategoryDTO categoryDTO) throws BusinessDoesNotExistException {
		//FIXME: categoryDAO.save(category);
		Business business = businessDAO.findById(businessId);
		
		Category category = new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category());
		
		business.getCategoryList().add(category);
		businessDAO.update(business);
	}


	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}


	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}


	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}


	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
	
}
