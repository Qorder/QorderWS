package com.qorder.qorderws.dto.product;

import java.math.BigDecimal;

import com.qorder.qorderws.utils.providers.ReferenceProvider;

public class DetailedProductDTO {

	private long id;
	private String name;
	private BigDecimal price;
	private String details; //seperator - 
	private String description;
	private final String imageRequestURI = ReferenceProvider.INSTANCE.getURIfor("image");
	
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

	public String getImageRequestURI() {
		return imageRequestURI + String.valueOf(id);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
