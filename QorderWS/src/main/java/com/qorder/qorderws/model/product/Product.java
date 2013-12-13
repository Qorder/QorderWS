package com.qorder.qorderws.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PRICE")
	private BigDecimal price;

	// private List<String> details;

	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}

	public Product() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<String> getDetails() {
		List<String> details = new ArrayList<String>();
		details.add("Detail 1");
		details.add("detail 2");
		details.add("detail 3");
		return details;
	}

	/*
	public void setDetails(List<String> details) {
		this.details = details;
	}*/
}
