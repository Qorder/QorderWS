package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
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
		} catch(final HibernateException ex){
			//sessionFactory.getCurrentSession().getTransaction().rollback();
		}
			return false;
	}
	
	/* Only single-row updates
	 * TODO : Throws BusinessDoesNotExistException
	 */
	@Override
	public boolean update(Business business) {
		//TODO : kalese tin find gia na deis an iparxei prin pas na to diagrapseis: h vres hiber. opti tropo.
		try {
			sessionFactory.getCurrentSession().update(business);
			return true;
		} catch(final HibernateException ex){
			//sessionFactory.getCurrentSession().getTransaction().rollback();
		}
			return false;
	}

	/* 
	// TODO : Throws BusinessDoesNotExistException
	 * @Return boolean
	*/
	@Override
	public boolean delete(Business business) throws BusinessDoesNotExistException {
		//TODO : kalese tin find gia na deis an iparxei prin pas na to diagrapseis: h vres hiber. opti tropo.
		try {
			sessionFactory.getCurrentSession().delete(business);
			return true;
		} catch(final HibernateException ex){
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return false;
	}
	
	/*
	 * @Return "filled" Business object if the id exists 
	 * @Throws BusinessDoesNotExistException 
	 */
	@Override
	public Business findById(long businessId) throws BusinessDoesNotExistException {
		Business business = null;
		try {
			business = (Business) sessionFactory.getCurrentSession().get(Business.class, businessId);
			if (business==null) {
				throw new BusinessDoesNotExistException();	
			}
		}
		catch(final HibernateException ex) {	
		}
		return business;
	}


}
