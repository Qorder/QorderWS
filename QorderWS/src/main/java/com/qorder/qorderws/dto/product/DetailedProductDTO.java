package com.qorder.qorderws.dto.product;

import java.math.BigDecimal;

public class DetailedProductDTO {

	private long id;
	private String name;
	private BigDecimal price;
	private String details; //seperator - 
	private String imageRequestURI = "http://snf-185147.vm.okeanos.grnet.gr:8080/qorderws/images/product?id=";
	
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

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
}
