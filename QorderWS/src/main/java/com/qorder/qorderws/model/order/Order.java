package com.qorder.qorderws.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ORDER_LIST")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_LIST_ID")
	private long id;

	@Column(name = "TABLE_NUMBER")
	private String tableNumber;

	@Column(name = "DATE_TIME")
	private Date dateTime;

	@OneToMany(targetEntity = ProductHolder.class)
	@JoinColumn(name = "ORDER_LIST_ID")
	private List<IProductHolder> orderList = new ArrayList<IProductHolder>();

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

	public List<IProductHolder> getOrderList() {
		return this.orderList;
	}

	public void addProductOrder(IProductHolder orderProd) {
		this.orderList.add(orderProd);
	}

}
