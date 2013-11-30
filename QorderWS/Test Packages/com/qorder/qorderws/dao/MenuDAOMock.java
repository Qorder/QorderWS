package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.category.ICategory;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuDAOMock implements IMenuDAO {

	private List<ICategory> mockDB = new ArrayList<ICategory>();
	
	public MenuDAOMock() {
		
		for(int i=0;i<5;i++)
		{
			ICategory category = new Category();
			category.setId(i);
			category.setName(String.valueOf(i));
			mockDB.add(category);
		}
	}
	
	@Override
	public List<ICategory> getCategoryListById(long businessId) {
		return mockDB;
	}

}
