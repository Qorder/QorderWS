package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryDAO implements ICategoryDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Category> fetchCategoriesForBusiness(long businessId)
			throws BusinessDoesNotExistException {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"SELECT CategoryList FROM Business as b WHERE b.id= :id");
		q.setParameter("id", businessId);
		List<Category> fetchedList = q.list();
		return fetchedList;
	}

	@Override
	public Category findById(long categoryId) throws CategoryDoesNotExistException {

		Category category = (Category) sessionFactory.getCurrentSession().get(Category.class, categoryId);
		if (category == null) 
		{
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
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return false;
	}

}
