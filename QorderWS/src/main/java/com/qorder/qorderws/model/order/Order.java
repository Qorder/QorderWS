package com.qorder.qorderws.model.order;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
	private String dateTime = DateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG, Locale.US).format(new Date());

	@OneToMany(targetEntity = ProductHolder.class, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "ORDER_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductHolder> orderList = new ArrayList<ProductHolder>();

	@ManyToOne(targetEntity = Business.class)
	@JoinColumn(name = "BUSINESS_ID")
	private Business business;
	
	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;
	
	@Column(name = "ORDER_STATUS")
	private EOrderStatus status = EOrderStatus.PENDING;

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

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public List<ProductHolder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ProductHolder> orderList) {
		this.orderList = orderList;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public void add(ProductHolder productHolder) {
		orderList.add(productHolder);
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public EOrderStatus getStatus() {
		return status;
	}

	public void setStatus(EOrderStatus orderStatus) {
		this.status = orderStatus;
	}
	
}
