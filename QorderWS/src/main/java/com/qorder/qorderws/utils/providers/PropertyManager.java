package com.qorder.qorderws.utils.providers;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.el.PropertyNotFoundException;
import java.io.IOException;
import java.util.Properties;

public final class PropertyManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyManager.class);

	private Properties propertiesFile = null;
	
	public PropertyManager(String propertyPath) {
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
