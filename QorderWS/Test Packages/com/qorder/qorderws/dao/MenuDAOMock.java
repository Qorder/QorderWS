package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuDAOMock implements IMenuDAO {

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

}
