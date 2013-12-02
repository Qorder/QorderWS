package com.qorder.qorderws.service.dao;

import java.util.List;

import com.qorder.qorderws.model.category.Category;

public interface IMenuDAO {
	
	List<Category> getCategoryListById(long businessId);

}
