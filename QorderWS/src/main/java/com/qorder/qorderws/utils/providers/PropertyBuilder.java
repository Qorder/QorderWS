package com.qorder.qorderws.utils.providers;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public final class PropertyBuilder {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyBuilder.class);
	private Properties properties = null;

	public PropertyBuilder(String propertyPath) {
		constructProperties(propertyPath);
	}

	private void constructProperties(String propertyPath) {
		try 
		{
			Resource resource = new ClassPathResource(propertyPath); //"references.properties"
			properties = PropertiesLoaderUtils.loadProperties(resource);
		} 
		catch (IOException e) 
		{
			LOGGER.debug("Reference property file not found!! Exception: " + e.getLocalizedMessage(), e);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return property file
	 * @return null if no property file found
	 */
	public Properties getProperties() {
		return properties;
	}

}
