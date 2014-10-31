package com.qorder.qorderws.dto.product;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class DetailedProductDTO {

	private String id;
	private String name;
	private String price;
	private String details; //seperator - 
	private String description;
	private final String imageRequestURI = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.PRODUCT_IMAGE);
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
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
