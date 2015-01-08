package com.qorder.qorderws.utils.providers;

import com.qorder.qorderws.model.EEntity;


/**
 * A singleton instance path provider for the entities of the domain.
 *
 * @author Grigorios
 * @see PropertyManager
 */
public enum EDomainLinkProvider {
	INSTANCE;

	private final PropertyManager propManager = new PropertyManager("references/domainReferences.properties");

	/**
	 * Returns a complete Http path for the requested entity.
	 *
	 * @param entity the type of entity
	 * @return String representation of the http path
	 */
	public String getHttpPathFor(EEntity entity) {
		return propManager.getProperty("host") + propManager.getProperty(entity.getName());
	}

	/**
	 * Returns the URI location for the requested entity
	 *
	 * @param entity the type of entity
	 * @return String representation of the URI location
	 */
	public String getLocationFor(EEntity entity) {
		return propManager.getProperty(entity.getName());
	}

	/**
	 * Returns the domain name of the host machine.
	 *
	 * @return name of the host
	 */
	public String getHost() {
		return propManager.getProperty("host");
	}
}
