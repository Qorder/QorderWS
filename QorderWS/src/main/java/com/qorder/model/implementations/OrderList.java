package com.qorder.model.implementations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qorder.model.interfaces.IOrderList;
import com.qorder.model.interfaces.IOrderProduct;

public class OrderList implements  IOrderList {
	
	private long id;
	private String tableNumber;
	private Date dateTime;
	private List<IOrderProduct> orderProductList = new ArrayList<IOrderProduct>();
	
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
	public List<IOrderProduct> getOrderProductList() {
		return this.orderProductList;
	}
	@Override
	public void addOrderProduct(IOrderProduct orderProd) {
		this.orderProductList.add(orderProd);
	}
	
	

}
