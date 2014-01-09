package com.qorder.qorderws.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

@Transactional
public class BusinessDAO implements IBusinessDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessDAO.class);
	private String ExceptionTime;

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
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to save business, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

	/*
	 * Only single-row updates TODO : Throws BusinessDoesNotExistException
	 */
	@Override
	public boolean update(Business business) {
		try {
			sessionFactory.getCurrentSession().update(business);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to update business, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

	/*
	 * // TODO : Throws BusinessDoesNotExistException
	 * 
	 * @Return boolean
	 */
	@Override
	public boolean delete(Business business)
			throws BusinessDoesNotExistException {
		// TODO : kalese tin find gia na deis an iparxei prin pas na to
		// diagrapseis: h vres hiber. opti tropo.
		try {
			sessionFactory.getCurrentSession().delete(business);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete business, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

	/*
	 * @Return "filled" Business object if the id exists
	 * 
	 * @Throws BusinessDoesNotExistException
	 */
	@Override
	public Business findById(long businessId)
			throws BusinessDoesNotExistException {
		Business business = null;
		try {
			business = (Business) sessionFactory.getCurrentSession().get(Business.class, businessId);
			if (business == null) {
				throw new BusinessDoesNotExistException();
			}
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to find business by id, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return business;
	}

}
