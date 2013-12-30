package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;
@Transactional
public class ProductDAO implements IProductDAO {
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> fetchProductsForCategory(long categoryId) throws CategoryDoesNotExistException {
		List<Product> fetchedList= sessionFactory.getCurrentSession()
				.createQuery("SELECT productList FROM Category as c WHERE c.id= :id").
				setParameter("id", categoryId).list();
			 return fetchedList;
	}
	
	
	@Override
	public Product findById(long productId) throws ProductDoesNotExistException {
		Product product=null;
		try {
			product = (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
			if (product==null) {
				throw new ProductDoesNotExistException();	
			}
		}
		catch(final HibernateException ex) {	
		}
		return product;
	}

	@Override
	public boolean save(Product product) {
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
		} catch(final HibernateException ex){
		}
		return false;
	}

	//TODO : Throws ProductDoesNotExistException
	@Override
	public boolean update(Product product) throws ProductDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch(final HibernateException ex){
		}
		return false;
	}

	//TODO : Throws ProductDoesNotExistException
	@Override
	public boolean delete(Product product) throws ProductDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
		} catch(final HibernateException ex){
			sessionFactory.getCurrentSession().getTransaction().rollback();
		}
		return false;	
	}

}