package com.qorder.qorderws.service;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

public interface ICategoryService {
	
	boolean createCategory(long businessId, Category category) throws BusinessDoesNotExistException;

}
