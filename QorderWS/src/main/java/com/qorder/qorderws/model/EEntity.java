package com.qorder.qorderws.model;

/**
 * Enumeration of entities followed by their names.
 * Useful when it comes to request links and paths from providers,
 * for the above registered values.
 *
 * @author Grigoris
 */
public enum EEntity {

	BUSINESS("business"),
	MENU("menu"),
	CATEGORY("category"),
	PRODUCT("product"),
	ORDER("order"),
	PRODUCT_IMAGE("image.product");
	
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
