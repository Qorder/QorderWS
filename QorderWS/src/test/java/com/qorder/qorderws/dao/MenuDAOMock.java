package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;

import com.qorder.qorderws.model.category.Category;

public class MenuDAOMock implements IMenuDAO {

	private SessionFactory sessionFactory = null;
	private List<Category> mockDB = new ArrayList<Category>();
	
	public MenuDAOMock() {
		
		for(int i=0;i<5;i++)
		{
			Category category = new Category();
			category.setId(i);
			category.setName(String.valueOf(i));
			mockDB.add(category);
		}
	}
	
	@Override
	public List<Category> getCategoryListById(long businessId) {
		return mockDB;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
