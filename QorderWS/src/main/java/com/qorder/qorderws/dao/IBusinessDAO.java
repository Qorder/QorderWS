package com.qorder.qorderws.dao;

import com.qorder.qorderws.model.business.Business;

public interface IBusinessDAO {
	
	long add(Business business);
	void update(Business business);
	int delete(long businessId);
	Business findById(long businessId);
	public int check();
}
