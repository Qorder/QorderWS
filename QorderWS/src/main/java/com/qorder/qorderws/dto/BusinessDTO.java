package com.qorder.qorderws.dto;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class BusinessDTO {

	private String name;
	
	private long menuId;
	
	private final String menuRef = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.MENU);

	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	public String getMenuRef() {
		return menuRef + menuId;
	}
}
