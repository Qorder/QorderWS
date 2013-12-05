package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.qorder.qorderws.model.category.Category;

public interface ICategoryDAO {
	
	public void setSessionFactory(SessionFactory sessionFactory);
	public List<Category> loadCategoriesByBusinessId(int businessId);

}
