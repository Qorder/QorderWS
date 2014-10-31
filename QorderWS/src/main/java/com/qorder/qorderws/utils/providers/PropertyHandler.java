package com.qorder.qorderws.utils.providers;

import java.io.IOException;
import java.util.Properties;

import javax.el.PropertyNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.google.common.base.Strings;

public final class PropertyHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyHandler.class);

	private Properties propertiesFile = null;
	
	public PropertyHandler(String propertyPath) {
		loadProperties(propertyPath);
	}
	
	public String getProperty(String key) throws PropertyNotFoundException {
		String value = propertiesFile.getProperty(key);
		if(Strings.isNullOrEmpty(value)) {
			throw new PropertyNotFoundException("No bound property to key: " + key);
		}
		return value;
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
