package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.ABusiness;
import com.qorder.qorderws.model.business.Business;

public interface IBusinessDAO {
	
	void save(Business business) throws PersistanceLayerException;
	
	void update(Business business) throws PersistanceLayerException;
	
	void delete(Business business) throws PersistanceLayerException;
	
	ABusiness findById(long businessId) throws PersistanceLayerException, ResourceNotFoundException;
}
