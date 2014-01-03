package com.qorder.qorderws.dto.product;

import java.math.BigDecimal;

import com.qorder.qorderws.utils.ReferenceProvider;

public class ProductDTO {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private String uri = ReferenceProvider.getURIFor("product");
	
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
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
}
