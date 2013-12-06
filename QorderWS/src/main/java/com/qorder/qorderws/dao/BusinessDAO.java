package com.qorder.qorderws.dao;

import org.hibernate.SessionFactory;
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
	public boolean save(Business business) {

		sessionFactory.getCurrentSession().save(business);
		return true;
	}

	@Override
	public boolean update(Business business) {
		sessionFactory.getCurrentSession().update(business);
		return true;
	}

	@Override
	public boolean delete(Business business) {
		sessionFactory.getCurrentSession().delete(business);
		return true;
	}
	
	@Override
	public Business findById(long businessId) {
			Business business= (Business) sessionFactory.getCurrentSession().get(Business.class, businessId);
			return business;
		   
	}


}
