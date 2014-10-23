package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

public class CategoryDTO {
	
	private String id;
	private String name;
	private final String categoryURI = ReferenceProvider.INSTANCE.getHttpPathFor(EEntity.CATEGORY);
	
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
	
	public String getUri() {
		return categoryURI + id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
