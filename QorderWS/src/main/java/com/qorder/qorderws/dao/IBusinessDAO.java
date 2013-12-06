package com.qorder.qorderws.dao;

import com.qorder.qorderws.model.business.Business;

public interface IBusinessDAO {
	
	boolean save(Business business);
	boolean update(Business business);
	boolean delete(Business businessId);
	Business findById(long businessId);

}
