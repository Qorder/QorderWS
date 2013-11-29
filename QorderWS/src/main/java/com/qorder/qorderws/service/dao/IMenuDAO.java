package com.qorder.qorderws.service.dao;

import java.util.List;

public interface IMenuDAO {
	
	List getProxyCategoryById(long businessId);
	
	List getCategoryById(long businessId);

}
