package com.qorder.qorderws.service;

import java.util.Iterator;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class ProductServiceMock implements IProductService {

	private IBusinessDAO businessDAO;

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	@Override
	public DetailedProductDTO fetchProductById(long productId) {
		Business business;
		try {
			business = businessDAO.findById(0);
			Iterator<Category> categoryItr = business.getCategoryList().iterator();
			while (categoryItr.hasNext()) {
				Iterator<Product> productItr = categoryItr.next()
						.getProductList().iterator();
				while (productItr.hasNext()) {
					Product product = productItr.next();
					if (product.getId() == productId)
						return new ProductToDetailedProductDTOMapper().map( product, new DetailedProductDTO());
				}
			}
		} catch (BusinessDoesNotExistException e) {
			e.printStackTrace();
		}
		return null;
	}

}
