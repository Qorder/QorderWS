package com.qorder.qorderws.dao;

import com.qorder.qorderws.model.product.Product;

public interface IProductDAO {

	Product findById(long productId);
}