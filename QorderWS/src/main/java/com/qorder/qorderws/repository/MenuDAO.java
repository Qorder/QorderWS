package com.qorder.qorderws.repository;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.menu.Menu;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
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
	public Menu save(Menu menu) throws PersistanceLayerException {
		try
		{
			sessionFactory.getCurrentSession().save(menu);
			return menu;
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to save menu, info: " + ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void update(Menu menu) throws PersistanceLayerException {
		try
		{
			sessionFactory.getCurrentSession().update(menu);
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to update business, info: " + ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public void delete(Menu menu) throws PersistanceLayerException {
		try
		{ 
			sessionFactory.getCurrentSession().delete(menu);
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to delete menu, info: " + ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

	@Override
	public Menu findById(long menuId) throws PersistanceLayerException, ResourceNotFoundException {
		try
		{
		 	Menu menu = (Menu) sessionFactory.getCurrentSession().get(Menu.class, menuId);
		 	if(menu == null)
		 	{
		 		throw new ResourceNotFoundException("Menu not found!");
		 	}
		 	return menu;
		}
		catch(final HibernateException ex)
		{
			LOGGER.warn("Hibernate exception was thrown while trying to fetch menu, info: " + ex.getLocalizedMessage(), ex);
			throw new PersistanceLayerException();
		}
	}

}
