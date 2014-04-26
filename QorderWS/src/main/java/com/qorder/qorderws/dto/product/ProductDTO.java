package com.qorder.qorderws.dto.product;

import java.math.BigDecimal;

import com.qorder.qorderws.utils.providers.ReferenceProvider;

public class ProductDTO {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private final String uri = ReferenceProvider.INSTANCE.getURIfor("product");
	
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
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
}
