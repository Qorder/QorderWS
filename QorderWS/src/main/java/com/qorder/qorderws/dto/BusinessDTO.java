package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class BusinessDTO {
	
	private String id;
	private String name;
	
	private String menuId;
	private final String menuURI = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.MENU);
	
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
	
	public String getMenuId() {
		return menuId;
	}
	
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuURI() {
		return menuURI + id;
	}
}
