package com.qorder.qorderws.dao;

import java.util.List;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.Business;

public interface IBusinessDAO {
	
	Business save(Business business) throws PersistanceLayerException;
	
	void update(Business business) throws PersistanceLayerException;
	
	void delete(Business business) throws PersistanceLayerException;
	
	Business findById(long businessId) throws PersistanceLayerException, ResourceNotFoundException;

	List<Business> fetchUserBusinesses(Long userId) throws PersistanceLayerException, ResourceNotFoundException;
}
