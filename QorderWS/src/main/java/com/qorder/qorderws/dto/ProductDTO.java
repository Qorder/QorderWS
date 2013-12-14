package com.qorder.qorderws.dto;

import java.math.BigDecimal;

public class ProductDTO {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private String uri = "http://snf-185147.vm.okeanos.grnet.gr:8080/qorderws/products/product?id=";
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
	
	public void setPrice(BigDecimal bigDecimal) {
		this.price = bigDecimal;
	}

	public String getUri() {
		return uri + id;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
}
