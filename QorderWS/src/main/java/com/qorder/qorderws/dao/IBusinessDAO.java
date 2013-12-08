package com.qorder.qorderws.dao;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

public interface IBusinessDAO {
	
	boolean save(Business business);
	boolean update(Business business) throws BusinessDoesNotExistException;
	boolean delete(Business business) throws BusinessDoesNotExistException;
	Business findById(long businessId) throws BusinessDoesNotExistException;
}
