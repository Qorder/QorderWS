package com.qorder.qorderws.utils.providers;

import com.qorder.qorderws.model.EEntity;



public enum EDomainLinkProvider {
	INSTANCE;

	private final PropertyManager propManager = new PropertyManager("references/domainReferences.properties");

	public String getHttpPathFor(EEntity entity) {
		return propManager.getProperty("host") + propManager.getProperty(entity.getName());
	}
	
	public String getLocationFor(EEntity entity) {
		return propManager.getProperty(entity.getName());
	}
	
	public String getHost() {
		return propManager.getProperty("host");
	}
}
