package com.qorder.qorderws.utils.providers;



public enum ReferenceProvider {
	INSTANCE;

	private final PropertyHandler propHandler = new PropertyHandler("references.properties");

	public String getPathFor(String entityName) {
		return propHandler.getProperty("host") + propHandler.getProperty(entityName);
	}
	
	public String getLocationFor(String entityName) {
		return propHandler.getProperty(entityName);
	}
	
	public String getHost() {
		return propHandler.getProperty("host");
	}
}
