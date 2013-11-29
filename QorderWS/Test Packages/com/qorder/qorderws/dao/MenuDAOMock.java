package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.ICategory;
import com.qorder.qorderws.model.category.ProxyCategory;
import com.qorder.qorderws.service.dao.IMenuDAO;

public class MenuDAOMock implements IMenuDAO {

	private List mockDB = new ArrayList<ICategory>();
	
	public MenuDAOMock() {
		
		for(int i=0;i<5;i++)
		{
			ProxyCategory category = new ProxyCategory();
			category.setId(i);
			category.setName(String.valueOf(i));
			category.setUriToReal("href: none");
			mockDB.add(category);
		}
	}
	
	@Override
	public List getProxyCategoryById(long businessId) {
		return mockDB;
	}

	@Override
	public List getCategoryById(long businessId) {
		// TODO Auto-generated method stub
		return null;
	}

}
