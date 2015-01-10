package com.qorder.qorderws.utils.providers;

import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * A utility class that loads and manages java properties files.
 *
 * @author Grigorios
 */
public final class PropertyManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(PropertyManager.class);

	private Properties propertiesFile = null;
	
	public PropertyManager(@NotNull String propertyPath) {
		String path = Objects.requireNonNull(propertyPath, "Path can not be null!");
		loadProperties(path);
	}

	/**
	 * Returns the value of a property, defined by the provided
	 * key given as parameter for this method.
	 *
	 * @param  key name of the property
	 * @return String value of property or
	 *         empty string if no property value exists
	 *         for the provided key.
	 */
	public String getProperty(@NotNull String key) {
		String value = propertiesFile.getProperty(key);
		if(Strings.isNullOrEmpty(value)) {
			LOGGER.warn("No bound property to key: {1}", key);
			value = "";
		}
		return value;
	}

	/**
	 * Loads property file from path.
	 *
	 * @param propertyPath path for the properties file
	 */
	private void loadProperties(String propertyPath) {
		try {
			propertiesFile = PropertiesLoaderUtils.loadProperties(new ClassPathResource(propertyPath));
		} catch (IOException e)	{
			LOGGER.warn("Reference property file not found!! Exception: {1}", e.getLocalizedMessage());
			propertiesFile = new Properties();
		}
	}
}
