package com.qorder.qorderws.dao;

import java.util.List;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;

public interface IProductDAO {

	boolean save(Product product);
	boolean update(Product product) throws ProductDoesNotExistException;
	boolean delete(Product product) throws ProductDoesNotExistException;
	List<Product> fetchProductsForCategory(long categoryId) throws CategoryDoesNotExistException;
	Product findById(long productId) throws ProductDoesNotExistException;
}