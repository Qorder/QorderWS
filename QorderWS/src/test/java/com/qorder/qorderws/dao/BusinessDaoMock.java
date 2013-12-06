package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

public class BusinessDaoMock implements IBusinessDAO {

	private static List<Business> businessList = new ArrayList<Business>();

	public BusinessDaoMock() {
		businessList.add(new Business("Ta kala paidia"));
	}

	@Override
	public boolean save(Business business) {
		return businessList.add(business);
	}

	@Override
	public boolean update(Business business) throws BusinessDoesNotExistException {
		if(businessList.contains(business))
		{
			businessList.set(0, business);
			return true;
		}
		else
		{
			throw new BusinessDoesNotExistException();
		}
		
	}

	@Override
	public boolean delete(long businessId) {
		return false;
	}

	@Override
	public Business findById(long businessId)
			throws BusinessDoesNotExistException {
		if (businessId < businessList.size()) 
		{
			return businessList.get((int) businessId);
		} 
		else 
		{
			throw new BusinessDoesNotExistException();
		}
	}

}
