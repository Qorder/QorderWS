package com.qorder.qorderws.utils.providers;

import com.qorder.qorderws.model.EEntity;

public enum ESystemPathProvider {
	INSTANCE;
	
	private final PropertyManager propManager = new PropertyManager("references/systemReferences.properties");
	
	public String getDirectoryPathFor(EEntity entity) {
		return propManager.getProperty(entity.getName());
	}
	
	public String getDefaultPathFor(EEntity entity) {
		return propManager.getProperty(entity.getName() + ".default");
	}
}
