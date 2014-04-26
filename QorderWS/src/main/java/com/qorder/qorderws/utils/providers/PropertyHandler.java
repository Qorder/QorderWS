package com.qorder.qorderws.utils.providers;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public final class PropertyHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyHandler.class);

	private Properties propertiesFile = null;
	
	public PropertyHandler(String propertyPath) {
		loadProperties(propertyPath);
	}
	
	public String getProperty(String key) {
		String value = propertiesFile.getProperty(key);
		return value.equals(null) ? "property not found " : value;
	}
	
	public Properties getPropertiesFile() {
		return propertiesFile;
	}

	public void setPropertiesFile(Properties propertiesFile) {
		this.propertiesFile = propertiesFile;
	}
	
	private void loadProperties(String propertyPath) {
		try 
		{
			propertiesFile = PropertiesLoaderUtils.loadProperties(new ClassPathResource(propertyPath));
		} 
		catch (IOException e) 
		{
			LOGGER.warn("Reference property file not found!! Exception: " + e.getLocalizedMessage(), e);
			propertiesFile = new Properties();
		}
	}
}
