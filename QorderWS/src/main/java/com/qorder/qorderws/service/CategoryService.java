package com.qorder.qorderws.service;

import java.math.BigDecimal;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

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

	@Override
	public Category getCategoryByID(long categoryId) {
		Category category = new Category();
		category.setName("food");
		Product product = new Product();
		product.setName("souvlaki");
		product.setPrice(BigDecimal.valueOf(1,4));
		category.addProduct(product);
		return category;
	}
	
}
