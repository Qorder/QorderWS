package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

public class BusinessDTO {
	
	private long id;
	private String name;
	
	private Long menuId;
	private final String menuURI = ReferenceProvider.INSTANCE.getHttpPathFor(EEntity.MENU);
	
	public Long getId() {
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
	
	public Long getMenuId() {
		return menuId;
	}
	
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getMenuURI() {
		return menuURI + id;
	}
}
