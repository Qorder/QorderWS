package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;

public interface IProductDAO {

	boolean save(Product product);
	
	boolean update(Product product) throws ProductDoesNotExistException;
	
	boolean delete(Product product) throws ProductDoesNotExistException;
	
	Product findById(long productId) throws ProductDoesNotExistException;
}