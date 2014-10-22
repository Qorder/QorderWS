package com.qorder.qorderws.model;

public enum EEntity {
	BUSINESS("business"),
	MENU("menu"),
	CATEGORY("category"),
	PRODUCT("product"),
	ORDER("order"),
	IMAGE("image");
	
	private final String entityName;
	
	private EEntity(String entityName) {
		this.entityName = entityName;
	}

	@Override
	public String toString() {
		return entityName;
	}

	public String getName() {
		return entityName;
	}
	
}
