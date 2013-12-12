package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.DetailedProductDTO;

public interface IProductService {
	DetailedProductDTO fetchProductById(long productId);
}