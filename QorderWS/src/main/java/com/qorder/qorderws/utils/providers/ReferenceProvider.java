package com.qorder.qorderws.utils.providers;

import com.qorder.qorderws.model.EEntity;



public enum ReferenceProvider {
	INSTANCE;

	private final PropertyHandler propHandler = new PropertyHandler("references.properties");

	public String getHttpPathFor(EEntity entity) {
		return propHandler.getProperty("host") + propHandler.getProperty(entity.getName());
	}
	
	public String getLocationFor(EEntity entity) {
		return propHandler.getProperty(entity.getName());
	}
	
	public String getHost() {
		return propHandler.getProperty("host");
	}
}
