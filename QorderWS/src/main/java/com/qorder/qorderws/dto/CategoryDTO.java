package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class CategoryDTO {
	
	private String id;
	
	private String name;
	
	private final String categoryRequestURI = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.CATEGORY);
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
	public String getCategoryRequestUri() {
		return categoryRequestURI + id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
