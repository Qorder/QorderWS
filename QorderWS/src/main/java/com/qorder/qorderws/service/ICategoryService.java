package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;

public interface ICategoryService {
	
	void createCategory(long menuId, CategoryDTO categoryDTO) throws ResourceNotFoundException;
	
	ProductDTO[] fetchCategoryByID(long categoryId) throws ResourceNotFoundException;
	
}
