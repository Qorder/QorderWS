package com.qorder.qorderws.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class ProductDAO implements IProductDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProductDAO.class);
	private String ExceptionTime;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> fetchProductsForCategory(long categoryId)
			throws CategoryDoesNotExistException {
		List<Product> fetchedList = null;
		try {
			fetchedList = sessionFactory
					.getCurrentSession()
					.createQuery(
							"SELECT productList FROM Category as c WHERE c.id= :id")
					.setParameter("id", categoryId).list();
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to fetch categories for business, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		if (fetchedList == null) {
			throw new CategoryDoesNotExistException();
		}
		return fetchedList;
	}

	@Override
	public Product findById(long productId) throws ProductDoesNotExistException {
		Product product = null;
		try {
			product = (Product) sessionFactory.getCurrentSession().get(
					Product.class, productId);
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to find product by id, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		if (product == null) {
			throw new ProductDoesNotExistException();
		}
		return product;
	}

	@Override
	public boolean save(Product product) {
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to save product, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

	// TODO : Throws ProductDoesNotExistException
	@Override
	public boolean update(Product product) throws ProductDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to update product, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

	// TODO : Throws ProductDoesNotExistException
	@Override
	public boolean delete(Product product) throws ProductDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(
					DateFormat.LONG, DateFormat.LONG, Locale.US).format(
					new Date());
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete product, info: "
							+ ex.getLocalizedMessage(), ex, this.ExceptionTime);
		}
		return false;
	}

}