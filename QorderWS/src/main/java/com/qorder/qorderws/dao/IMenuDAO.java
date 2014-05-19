package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.menu.Menu;

public interface IMenuDAO {
	
	void save(Menu menu) throws PersistanceLayerException;
	
	void update(Menu menu) throws PersistanceLayerException;
	
	void delete(Menu menu) throws PersistanceLayerException;
	
	Menu findById(long menuId) throws PersistanceLayerException, ResourceNotFoundException;
}
