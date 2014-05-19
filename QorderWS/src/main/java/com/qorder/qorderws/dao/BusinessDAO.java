package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.Business;

@Transactional
public class BusinessDAO implements IBusinessDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessDAO.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Business business) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().save(business);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to save business, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException("Save exception");
		}
	}

	/*
	 * Only single-row updates TODO : Throws BusinessDoesNotExistException
	 */
	@Override
	public void update(Business business) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().update(business);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update business, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException("Update exception");
		}
	}

	/*
	 * 
	 * @Return boolean
	 */
	@Override
	public void delete(Business business) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().delete(business);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete business, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException("Delete exception");
		}
	}

	/*
	 * @Return "filled" Business object if the id exists
	 */
	@Override
	public Business findById(long businessId) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			Business business = (Business) sessionFactory.getCurrentSession()
					.get(Business.class, businessId);
			if (business == null) {
				throw new ResourceNotFoundException("Business not found");
			}
			return business;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception raised while trying to find business by id, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException("Business not found");
		}
	}

}
