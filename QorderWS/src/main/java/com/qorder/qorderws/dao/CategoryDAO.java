package com.qorder.qorderws.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.model.category.Category;

@Transactional
public class CategoryDAO implements ICategoryDAO{
	
private SessionFactory sessionFactory = null;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	    
	@Override
	@SuppressWarnings("unchecked")
	public List<Category> loadCategoriesByBusinessId(int businessId){
		return this.sessionFactory.getCurrentSession()
					.createQuery("from CATEGORY where BUSINESS_ID=:businessId")
	    			.setParameter("businessId", businessId)
	    			.list();
	    		
	    }
	
	public long saveCategory(Category category){
			sessionFactory.getCurrentSession().save(category);
			return category.getId();
		}
		
		
	
	    
	    

}
