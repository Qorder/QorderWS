package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderDAO implements IOrderDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(OrderDAO.class);

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
		} catch (final HibernateException ex) {
			LOGGER.info(
					"An exception was raised, details: "
							+ ex.getLocalizedMessage(), ex);
		}
		return false;
	}

	@Override
	public boolean update(Order order) throws OrderDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(order);
			return true;
		} catch (final HibernateException ex) {
		}

		return false;
	}

	@Override
	public boolean delete(Order order) {
		try {
			sessionFactory.getCurrentSession().delete(order);
			return true;
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete order, info: ",
					ex.getLocalizedMessage());
		}

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
	public List<Order> fetchOrderForBusiness(long businessId)
			throws BusinessDoesNotExistException {
		List<Order> fetchedList = sessionFactory.getCurrentSession()
				.createCriteria(Order.class)
				.add(Restrictions.eq("business.id", businessId)).list();

		if (fetchedList == null) {
			throw new BusinessDoesNotExistException();
		}
		return fetchedList;
	}

}
