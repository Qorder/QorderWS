package com.qorder.model;

import java.util.Date;

public interface IOrderList {
	
	public int getId();
	
	//ta parakatw se string, giati mporei t store na exei arithmisi 11-4, 11-5, A-1 klp.
	public String getTableNumber();
	public void setTableNumber(String tableNumber);
	public Date getDateTime();
	public void setDateTime(); //pernei apo to sistima me Date()
	
	

}
