package com.qorder.qorderws.dao;

import org.hibernate.SessionFactory;

import com.qorder.qorderws.model.product.Product;

public class ProductDAO implements IProductDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Product findById(long productId) {
		Product product = (Product) sessionFactory.getCurrentSession().get(Product.class, productId);
		return product;
	}
}
