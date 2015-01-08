package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class CategoryDTO {

	private long id;

	private String name;
	
	private final String href = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.CATEGORY);

	
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
	
	public String getHref() {
		return href + id;
	}
	
	public void setId(long id) {
		this.id = id;
	}

}
