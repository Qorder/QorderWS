package com.qorder.qorderws.service;

import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IProductDAO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.DetailedProductDTOtoProductMapper;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class ProductService implements IProductService {
	
	private IProductDAO productDAO;
	private ICategoryDAO categoryDAO;

	@Transactional(readOnly = true)
	@Override
	public DetailedProductDTO fetchProductById(long productId) throws ResourceNotFoundException {
		Product product = productDAO.findById(productId);
		return new ProductToDetailedProductDTOMapper().map(product, new DetailedProductDTO());
	}

	@Override
	public void storeProducts(long categoryId, DetailedProductDTO[] productsDTO) throws ResourceNotFoundException, IOException {
		Category category = categoryDAO.findById(categoryId);
		for(DetailedProductDTO productDTO : productsDTO)
		{
			Product product = new DetailedProductDTOtoProductMapper().map(productDTO,new Product());
			category.addProduct(product);
		}
		categoryDAO.update(category);
	}
	

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

}
