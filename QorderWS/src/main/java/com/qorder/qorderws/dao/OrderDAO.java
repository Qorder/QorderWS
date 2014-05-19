package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.order.EOrderStatus;
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
	public Order save(Order order) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().save(order);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to save order, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
		return order;
	}

	@Override
	public void update(Order order) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().update(order);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to update order, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void delete(Order order) throws PersistanceLayerException {
		try {
			sessionFactory.getCurrentSession().delete(order);
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to delete order, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public Order findById(long orderId) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			Order order = (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
			if(order == null) {
				throw new ResourceNotFoundException("Order not found!");
			}
			return order;
			
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to find order by id, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> fetchOrdersForBusiness(long businessId) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			List<Order> orderList = (List<Order>) sessionFactory.getCurrentSession()
					.createCriteria(Order.class)
					.add(Restrictions.eq("business.id", businessId))
					.list();
			
			if(orderList == null) {
				throw new ResourceNotFoundException("Orders not found");
			}
			return orderList;
			
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to fetch orders for business, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> fetchOrdersByStatus(long businessId,
			EOrderStatus orderStatus) throws PersistanceLayerException, ResourceNotFoundException {
		try {
			List<Order> orderList = (List<Order>) sessionFactory.getCurrentSession()
					.createCriteria(Order.class)
					.add(Restrictions.eq("business.id", businessId))
					.add(Restrictions.eq("status", orderStatus))
					.list();
			
			if(orderList == null) {
				throw new ResourceNotFoundException("Orders not found");
			}
			return orderList;
			
		} catch (final HibernateException ex) {
			LOGGER.warn(
					"Hibernate exception was raised while trying to fetch orders by status for business, info: "
							+ ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

}
