package com.qorder.qorderws.service;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IProductDAO;
import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class ProductService implements IProductService {
	
	private IProductDAO productDAO;
	private ICategoryDAO categoryDAO;

	@Override
	public DetailedProductDTO fetchProductById(long productId) throws ProductDoesNotExistException {
		Product product = productDAO.findById(productId);
		return new ProductToDetailedProductDTOMapper().map(product, new DetailedProductDTO());
	}

	@Override
	public void createProduct(long categoryId, Product product) throws CategoryDoesNotExistException, IOException {
		Category category = categoryDAO.findById(categoryId);
		category.addProduct(product);
		categoryDAO.update(category);
	}
	
	@ExceptionHandler({ CategoryDoesNotExistException.class })
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ IOException.class })
	ResponseEntity<String> sendIOException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
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
