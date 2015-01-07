package com.qorder.qorderws.repository;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.Business;

import java.util.List;

public interface IBusinessDAO {
	
	Business save(Business business) throws PersistanceLayerException;
	
	void update(Business business) throws PersistanceLayerException;
	
	void delete(Business business) throws PersistanceLayerException;
	
	Business findById(long businessId) throws PersistanceLayerException, ResourceNotFoundException;

	List<Business> fetchUserBusinesses(Long userId) throws PersistanceLayerException, ResourceNotFoundException;
}
