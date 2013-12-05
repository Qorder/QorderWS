package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.model.category.Category;

@Transactional
public class MenuDAO implements IMenuDAO {

	private SessionFactory sessionFactory = null;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> getCategoryListById(long businessId) {
		Session session = sessionFactory.getCurrentSession();
		//This is a test to see hibernates session and transation state
		if(session.isOpen() && session!=null && session.isConnected())
		{
			System.out.println("is open and works!!");
			
		}
		else
		{
			System.out.println("it is not working right");
		}
		return new ArrayList<Category>();
	}

}
