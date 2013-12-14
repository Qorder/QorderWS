package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.exception.ProductDoesNotExistException;

public interface IProductService {
	DetailedProductDTO fetchProductById(long productId) throws ProductDoesNotExistException;
}