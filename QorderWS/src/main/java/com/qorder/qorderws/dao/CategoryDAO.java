package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
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
	public Category findById(long categoryId)
			throws CategoryDoesNotExistException {
		Category category = null;
		try {
			category = (Category) sessionFactory.getCurrentSession().get(
					Category.class, categoryId);

		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to find category by id, info: "
							+ ex.getLocalizedMessage(), ex);
		}
		if (category == null) {
			throw new CategoryDoesNotExistException();
		}
		return category;
	}

	@Override
	public boolean save(Category category) {
		try {
			sessionFactory.getCurrentSession().save(category);
			return true;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to save category, info: "
							+ ex.getLocalizedMessage(), ex);
		}
		return false;
	}

	// TODO : Throws CategoryDoesNotExistException
	@Override
	public boolean update(Category category)
			throws CategoryDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update category, info: "
							+ ex.getLocalizedMessage(), ex);
		}
		return false;
	}

	// TODO : Throws CategoryDoesNotExistException
	@Override
	public boolean delete(Category category)
			throws CategoryDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().delete(category);
			return true;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete category, info: "
							+ ex.getLocalizedMessage(), ex);
		}
		return false;
	}

}
