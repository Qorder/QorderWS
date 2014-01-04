package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;

public interface IProductService {
	DetailedProductDTO fetchProductById(long productId) throws ProductDoesNotExistException;
	
	void storeProducts(long categoryId, DetailedProductDTO[] productsDTO) throws CategoryDoesNotExistException, IOException;
}