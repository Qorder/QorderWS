package com.qorder.qorderws.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderList {
	
	private long id;
	private String tableNumber;
	private Date dateTime;
	private List<IProductOrder> productOrderList = new ArrayList<IProductOrder>();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getTableNumber() {
		return tableNumber;
	}


	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public Date getDateTime() {
		return dateTime;
	}
	
	public void setDateTime() {
		this.dateTime = new Date();
	}


	public List<IProductOrder> getOrderProductList() {
		return this.productOrderList;
	}

	public void addProductOrder(IProductOrder orderProd) {
		this.productOrderList.add(orderProd);
	}
	
	

}
