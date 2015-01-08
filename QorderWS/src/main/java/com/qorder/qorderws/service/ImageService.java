package com.qorder.qorderws.service;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.provider.IFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ImageService implements IImageService {


	private final IFileProvider imageProvider;

	@Autowired
	public ImageService(IFileProvider imageProvider) {
		this.imageProvider = imageProvider;
	}

	@Override
	public byte[] getImageFor(EEntity entity, long entityID) throws IOException {
		return imageProvider.getResourceByteArrayFor(entity, entityID);
	}
	
}
