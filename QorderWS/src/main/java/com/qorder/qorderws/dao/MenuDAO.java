package com.qorder.qorderws.dao;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qorder.qorderws.exception.MenuDoesNotExistException;
import com.qorder.qorderws.model.menu.Menu;

public class MenuDAO implements IMenuDAO {

	private SessionFactory sessionFactory;
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuDAO.class);
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Menu menu) {
		try
		{
			sessionFactory.getCurrentSession().save(menu);
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to save menu, info: " + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public void update(Menu menu) {
		try
		{
			sessionFactory.getCurrentSession().update(menu);
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to update business, info: " + ex.getLocalizedMessage(), ex);
		}
	}

	@Override
	public Menu delete(Long menuId) throws MenuDoesNotExistException {
		Menu menu = null;
		try
		{
			menu = findById(menuId); 
			sessionFactory.getCurrentSession().delete(menu);
			return menu;
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to delete menu, info: " + ex.getLocalizedMessage(), ex);
		}
		return menu;
	}

	@Override
	public Menu findById(long menuId) throws MenuDoesNotExistException {
		Menu menu = null;
		try
		{
			//Warn: it does not work!!!
		 	menu = (Menu) sessionFactory.getCurrentSession().get(Menu.class, menuId);
		 	if(menu == null)
		 	{
		 		throw new MenuDoesNotExistException();
		 	}
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to fetch menu, info: " + ex.getLocalizedMessage(), ex);
		}
		return menu;
	}

}
