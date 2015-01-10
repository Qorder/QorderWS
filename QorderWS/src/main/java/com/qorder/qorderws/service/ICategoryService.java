package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;

import javax.validation.constraints.NotNull;
import java.util.Collection;

public interface ICategoryService {
	
	Collection<ProductDTO> fetchCategoryByID(long categoryId);

	long addProduct(long categoryID, @NotNull DetailedProductDTO productDTO);

}
