package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.Order;
@Transactional
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
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Order> fetchOrderForBusiness(long businessId) throws OrderDoesNotExistException {
		List<Order> fetchedList = sessionFactory.getCurrentSession()
												.createCriteria(Order.class)
												.add(Restrictions.eq("business.id",businessId))
												.list(); 
		
		 return fetchedList;
	}
	

}
