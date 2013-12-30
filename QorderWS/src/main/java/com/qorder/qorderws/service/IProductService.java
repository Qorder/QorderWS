package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;

public interface IProductService {
	DetailedProductDTO fetchProductById(long productId) throws ProductDoesNotExistException;
	
	void createProduct(long categoryId, Product product) throws CategoryDoesNotExistException, IOException;
}