package com.qorder.qorderws.utils.providers;

import java.util.Properties;


public final class ReferenceProvider {
	
	private static final Properties refProperties = new PropertyBuilder("references.properties").getProperties();
	
	public static String getURIfor(String propertyName) {
		if(refProperties != null)
		{
			return getHost() + refProperties.getProperty(propertyName);
		}
		return "uri not found";
	}
	
	public final static String getHost() {
		return refProperties.getProperty("host");
	}

}
