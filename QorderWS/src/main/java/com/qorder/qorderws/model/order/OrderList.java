package com.qorder.qorderws.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderList implements  IOrderList {
	
	private long id;
	private String tableNumber;
	private Date dateTime;
	private List<IProductOrder> productOrderList = new ArrayList<IProductOrder>();
	
	//constructor xwris id giati to pernei afou kataxorithei sti vash
	public OrderList() {
		
	}
	
	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getTableNumber() {
		return tableNumber;
	}

	@Override
	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	@Override
	public Date getDateTime() {
		return dateTime;
	}
	
	@Override
	public void setDateTime() {
		this.dateTime = new Date();
	}

	@Override
	public List<IProductOrder> getOrderProductList() {
		return this.productOrderList;
	}
	@Override
	public void addProductOrder(IProductOrder orderProd) {
		this.productOrderList.add(orderProd);
	}
	
	

}
