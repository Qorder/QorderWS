package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class ProductDAO implements IProductDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ProductDAO.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Product findById(long productId) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			Product product = (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
			if(product == null) {
				throw new ResourceNotFoundException("Product not found!");
			}
			return product;
			
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to find product by id, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void save(Product product) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().save(product);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to save product, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void update(Product product) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().update(product);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update product, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void delete(Product product) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().delete(product);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete product, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

}