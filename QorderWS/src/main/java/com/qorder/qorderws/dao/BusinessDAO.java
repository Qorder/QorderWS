package com.qorder.qorderws.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.model.business.Business;

@Transactional
public class BusinessDAO implements IBusinessDAO {
	
	private SessionFactory sessionFactory = null;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public long add(Business business) {

		sessionFactory.getCurrentSession().save(business);
		return business.getId();
		//return 9000; //TODO : Na allaxthei me to id tis neas grammis.
	}

	@Override
	public void update(Business business) {
		// TODO Auto-generated method stub

	}

	@Override
	public int delete(long businessId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Business findById(long businessId) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int check() {
		Session session = sessionFactory.getCurrentSession();
		if(session.isOpen() && session!=null && session.isConnected())
		{
			System.out.println("is open and works!!");
			
		}
		else
		{
			System.out.println("it is not working right");
		}
		return 0;
	}

}
