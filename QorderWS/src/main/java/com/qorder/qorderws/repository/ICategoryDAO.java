package com.qorder.qorderws.repository;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryDAO {
	
	Category save(Category category) throws PersistanceLayerException;
	
	void update(Category category) throws PersistanceLayerException;
	
	void delete(Category category) throws PersistanceLayerException;
	
	Category findById(long categoryId) throws PersistanceLayerException, ResourceNotFoundException;
}
