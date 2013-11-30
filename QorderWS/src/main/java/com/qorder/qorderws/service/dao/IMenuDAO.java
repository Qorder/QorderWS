package com.qorder.qorderws.service.dao;

import java.util.List;

import com.qorder.qorderws.model.category.ICategory;

public interface IMenuDAO {
	
	List<ICategory> getCategoryListById(long businessId);

}
