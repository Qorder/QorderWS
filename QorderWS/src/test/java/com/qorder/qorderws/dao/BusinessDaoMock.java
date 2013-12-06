package com.qorder.qorderws.dao;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

public class BusinessDaoMock implements IBusinessDAO {

	private List<Business> businessList = new ArrayList<Business>();

	public BusinessDaoMock() {
		businessList.add(new Business("Ta kala paidia"));
		businessList.add(new Business("Petromulos"));
	}

	@Override
	public boolean save(Business business) {
		return businessList.add(business);
	}

	@Override
	public boolean update(Business business) throws BusinessDoesNotExistException {
		if(business.getId()< businessList.size())
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

	@Override
	public boolean delete(Business business) {
		// TODO Auto-generated method stub
		return false;
	}

}
