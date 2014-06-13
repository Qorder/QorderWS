package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.product.Product;

public interface IProductDAO {

	Product save(Product product) throws PersistanceLayerException;
	
	void update(Product product) throws PersistanceLayerException;
	
	void delete(Product product) throws PersistanceLayerException;
	
	Product findById(long productId) throws PersistanceLayerException, ResourceNotFoundException;
}