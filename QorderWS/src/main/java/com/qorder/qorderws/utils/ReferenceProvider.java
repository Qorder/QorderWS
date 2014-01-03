package com.qorder.qorderws.utils;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

public class ReferenceProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceProvider.class);
	
	public static String getURIfor(String entity) {
		String uri = null;
		try 
		{
			Resource resource = new ClassPathResource("references.properties");
			Properties props = PropertiesLoaderUtils.loadProperties(resource);
			
			uri = props.getProperty(entity);
		}
		catch (IOException e)
		{
			LOGGER.debug("Reference property file not found!! Exception: " + e.getLocalizedMessage(),e);
			e.printStackTrace();
		}
		LOGGER.debug(uri);
		return uri;
	}

}
