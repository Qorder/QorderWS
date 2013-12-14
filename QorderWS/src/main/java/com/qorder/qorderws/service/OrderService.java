
package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IBusinessDAO;
import com.qorder.qorderws.dao.IOrderDAO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.order.Order;

@Transactional
public class OrderService implements IOrderService {
	
	private IBusinessDAO businessDAO;
	private IOrderDAO orderDAO;
	
	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}


	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}


	public IOrderDAO getOrderDAO() {
		return orderDAO;
	}


	public void setOrderDAO(IOrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	@Override
	public boolean createOrder(long businessId, Order order) throws BusinessDoesNotExistException {
	
		Business business = businessDAO.findById(businessId);
		return orderDAO.save(order);
	}

}
