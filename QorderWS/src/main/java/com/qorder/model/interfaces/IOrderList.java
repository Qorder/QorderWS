package com.qorder.model.interfaces;

import java.util.Date;
import java.util.List;

public interface IOrderList {
	
	public long getId();
	public void setId(long id);
	public String getTableNumber();
	public void setTableNumber(String tableNumber);
	public Date getDateTime();
	public void setDateTime();
	public List<IOrderProduct> getOrderProductList();
	public void addOrderProduct(IOrderProduct orderProd);

}
