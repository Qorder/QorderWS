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
	public long storeProduct(long categoryId, DetailedProductDTO productDTO) throws ResourceNotFoundException, IOException {
		Product product = new DetailedProductDTOtoProductMapper().map(productDTO,new Product());
		productDAO.save(product);
		
		Category category = categoryDAO.findById(categoryId);
		category.addProduct(product);
		categoryDAO.update(category);
		
		return product.getId();
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
