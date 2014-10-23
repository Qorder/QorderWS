package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface IProductService {
	
	DetailedProductDTO fetchProductById(long productId) throws ResourceNotFoundException;
	
	long storeProduct(long categoryId, DetailedProductDTO productDTO) throws ResourceNotFoundException, IOException;
	
}