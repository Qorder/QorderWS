package com.qorder.qorderws.utils.providers;


public enum ReferenceProvider {
	INSTANCE;

	private final PropertyHandler provider = new PropertyHandler("references.properties");

	public String getURIfor(String entityName) {
		return provider.getProperty("host") + provider.getProperty(entityName);
	}
	
	public String getHost() {
		return provider.getProperty("host");
	}
}
