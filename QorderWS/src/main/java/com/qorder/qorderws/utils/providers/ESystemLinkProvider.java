package com.qorder.qorderws.utils.providers;

import com.qorder.qorderws.model.EEntity;

public enum ESystemLinkProvider {
	INSTANCE;
	
	private final PropertyHandler propHandler = new PropertyHandler("references/systemReferences.properties");
	
	public String getDirectoryFor(EEntity entity) {
		return propHandler.getProperty(entity.getName());
	}
	
	public String getDefaultPathFor(EEntity entity) {
		return propHandler.getProperty(entity.getName() + ".default");
	}
}
