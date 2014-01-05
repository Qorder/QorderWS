package com.qorder.qorderws.dao;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.OrderDoesNotExistException;
import com.qorder.qorderws.model.order.EOrderStatus;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderDAO implements IOrderDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderDAO.class);
	private String ExceptionTime; 

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Order save(Order order) {
		try 
		{
			sessionFactory.getCurrentSession().save(order);
		} 
		catch (final HibernateException ex) 
		{
			this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
		       LOGGER.warn(
		           "Hibernate exception was raised while trying to save order, info: " +
		           ex.getLocalizedMessage(),ex,this.ExceptionTime);
		}
		return order;
	}

	@Override
	public boolean update(Order order) throws OrderDoesNotExistException {
		try {
			sessionFactory.getCurrentSession().update(order);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
		       LOGGER.warn(
		           "Hibernate exception was raised while trying to update order, info: " +
		           ex.getLocalizedMessage(),ex,this.ExceptionTime);
		}
		return false;
	}

	@Override
	public boolean delete(Order order) {
		try {
			sessionFactory.getCurrentSession().delete(order);
			return true;
		} catch (final HibernateException ex) {
			this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
		       LOGGER.warn(
		           "Hibernate exception was raised while trying to delete order, info: " +
		           ex.getLocalizedMessage(),ex,this.ExceptionTime);
		}

		return false;
	}

	@Override
	public Order findById(long orderId) throws OrderDoesNotExistException {
		Order order = null;
		     try {
		       order = (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
		     }catch(final HibernateException ex) {
		    	 this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
			       LOGGER.warn(
			           "Hibernate exception was raised while trying to find order by id, info: " +
			           ex.getLocalizedMessage(),ex,this.ExceptionTime);
		     }
		     if (order==null){
		       throw new OrderDoesNotExistException();
		     }
		      return order;
		    
	}

	@Override
	@SuppressWarnings("unchecked")
		public List<Order> fetchOrderForBusiness(long businessId) throws BusinessDoesNotExistException {
			    List<Order> fetchedList = null;
			     try {
			    	 fetchedList = sessionFactory.getCurrentSession()
			    			 .createCriteria(Order.class)
			    			 .add(Restrictions.eq("business.id", businessId)).list();
			 
			     }catch(final HibernateException ex) {
			    	 this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
			    	 LOGGER.warn(
			    			 "Hibernate exception was raised while trying to fetch orders for business, info: " +
			    			 ex.getLocalizedMessage(),ex,this.ExceptionTime);
			     }
			      if (fetchedList == null) {
			        throw new BusinessDoesNotExistException();
			      }
			      return fetchedList;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Order> fetchOrdersByStatus(long businessId, EOrderStatus orderStatus) throws BusinessDoesNotExistException {
		List<Order> fetchedList = null;
		try {
			fetchedList = sessionFactory.getCurrentSession()
				.createCriteria(Order.class)
				.add(Restrictions.eq("business.id", businessId))
				.add(Restrictions.eq("status", orderStatus))
				.list();
		}catch(final HibernateException ex){
			this.ExceptionTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());
		       LOGGER.warn(
		           "Hibernate exception was raised while trying to fetch orders by status for business, info: " +
		           ex.getLocalizedMessage(),ex,this.ExceptionTime);
		}
		if (fetchedList == null) {
			throw new BusinessDoesNotExistException();
		}
		return fetchedList;
	}

}
