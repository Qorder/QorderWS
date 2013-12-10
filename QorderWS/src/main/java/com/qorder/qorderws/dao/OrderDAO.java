package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.Order;

public class OrderDAO implements IOrderDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean save(Order order) {
		try {
			sessionFactory.getCurrentSession().save(order);
			return true;
		} catch (final HibernateException ex) {}
	

		return false;
	}

	@Override
	public boolean update(Order order) throws OrderDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(order);
			return true;
		} catch (final HibernateException ex) {}
	
		return false;
	}

	@Override
	public boolean delete(Order order) {
		try {
			sessionFactory.getCurrentSession().delete(order);
			return true;
		} catch (final HibernateException ex) {}
		
		return false;
	}

	@Override
	public Order findById(long orderId) throws OrderDoesNotExistException {
		Order order = (Order) sessionFactory.getCurrentSession().get(
				Order.class, orderId);
		return order;
	}

}
