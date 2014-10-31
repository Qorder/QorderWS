package com.qorder.qorderws.service;

import java.io.IOException;

import com.qorder.qorderws.fao.IFileAccessObject;
import com.qorder.qorderws.model.EEntity;

public class ImageService implements IImageService {
	
	private IFileAccessObject imageFAO = null; 

	@Override
	public byte[] getImageFor(EEntity entity, long entityID) throws IOException {
		return imageFAO.getResourceByteArrayFor(entity, entityID);
	}

	public IFileAccessObject getImageFAO() {
		return imageFAO;
	}

	public void setImageFAO(IFileAccessObject imageFAO) {
		this.imageFAO = imageFAO;
	}
	
}
