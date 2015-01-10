package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;

public interface IProductService {
	
	DetailedProductDTO fetchProductById(long productId);
	
}