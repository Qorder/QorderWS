package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface ICategoryService {
	
	ProductDTO[] fetchCategoryByID(long categoryId) throws ResourceNotFoundException;

	long addProduct(long categoryID, DetailedProductDTO productDTO) throws ResourceNotFoundException;
	
}
