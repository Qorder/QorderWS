package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
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
		try {
		sessionFactory.getCurrentSession().save(business);
		return true;
		} catch(final HibernateException ex){}
		return false;
	}

	@Override
	public boolean update(Business business) {
		try {
		sessionFactory.getCurrentSession().update(business);
		return true;
		} catch(final HibernateException ex){}
		return false;
	}

	@Override
	public boolean delete(Business business) {
		try {
		sessionFactory.getCurrentSession().delete(business);
		return true;
		} catch(final HibernateException ex){}
		return false;
	}
	
	@Override
	public Business findById(long businessId) {
			Business business= (Business) sessionFactory.getCurrentSession().get(Business.class, businessId);
			return business;
		   
	}


}
