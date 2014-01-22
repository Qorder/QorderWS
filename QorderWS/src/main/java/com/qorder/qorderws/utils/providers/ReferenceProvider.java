package com.qorder.qorderws.utils.providers;

import java.util.Properties;


public final class ReferenceProvider {
	
	private static final Properties refProperties = new PropertyBuilder("references.properties").getProperties();
	
	public static String getURIfor(String propertyName) {
		return refProperties.getProperty("host") + refProperties.getProperty(propertyName);
	}

}
