package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryDAO implements ICategoryDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CategoryDAO.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Category findById(long categoryId) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			Category category = (Category) sessionFactory.getCurrentSession().get(
					Category.class, categoryId);
			if(category == null) {
				throw new ResourceNotFoundException("Category not found!");
			}
			return category;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to find category by id, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void save(Category category) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().save(category);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to save category, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	// TODO : Throws CategoryDoesNotExistException
	@Override
	public void update(Category category) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().update(category);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update category, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	// TODO : Throws CategoryDoesNotExistException
	@Override
	public void delete(Category category) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().delete(category);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete category, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

}
