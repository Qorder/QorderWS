package com.qorder.qorderws.model.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.business.Business;

@Entity
@Table(name = "ORDERS")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private long id;

	@Column(name = "TABLE_NUMBER")
	private String tableNumber;

	@Column(name = "DATE_TIME")
	private Date dateTime;

	@OneToMany(targetEntity = ProductHolder.class)
	@JoinColumn(name = "ORDER_ID")
	private List<ProductHolder> order = new ArrayList<ProductHolder>();
	
	@ManyToOne(targetEntity=Business.class,cascade=CascadeType.ALL )
    @JoinColumn(name="BUSINESS_ID")
	private long businessId;

	public long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(long businessId) {
		this.businessId = businessId;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public void setOrder(List<ProductHolder> order) {
		this.order = order;
	}

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

	public List<ProductHolder> getOrder() {
		return this.order;
	}

	public void addProductOrder(ProductHolder orderProd) {
		this.order.add(orderProd);
	}

}
