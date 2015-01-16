package com.qorder.qorderws.model.order;

import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.product.ProductHolder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "ORDER_ID")
	private Long id;

	@Column(name = "TABLE_NUMBER")
	private String tableNumber;

	@OneToMany(targetEntity = ProductHolder.class,fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "FK_ORDER_ID")
	private List<ProductHolder> orderList = new ArrayList<>();

	@ManyToOne(targetEntity = Business.class,fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "FK_BUSINESS_ID", nullable = false, updatable = false)
	private Business business;

	@Column(name = "TOTAL_PRICE")
	private BigDecimal totalPrice;

	@Enumerated(EnumType.STRING)
	@Column(name = "ORDER_STATUS")
	private EOrderStatus status = EOrderStatus.PENDING;

	@Column(name = "DATE_TIME")
	private String dateTime;

	public Order() {
		DateTimeFormatter simpleDateFormat  = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
		dateTime = LocalDateTime.now().format(simpleDateFormat); //like: Nov 16, 2014 11:03:40 PM
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
		totalPrice = new BigDecimal("0");
		for (ProductHolder productHolder : orderList) {
			totalPrice = totalPrice.add(productHolder.getHoldingProductsPrice());
		}
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
