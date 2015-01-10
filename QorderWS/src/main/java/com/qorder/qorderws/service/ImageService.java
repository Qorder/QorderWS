package com.qorder.qorderws.service;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.provider.IFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
public class ImageService implements IImageService {


	private final IFileProvider imageProvider;

	@Autowired
	public ImageService(IFileProvider imageProvider) {
		this.imageProvider = imageProvider;
	}

	@Override
	public byte[] getImageFor(@NotNull EEntity entity, long entityID) {
		return imageProvider.getResourceByteArrayFor(entity, entityID);
	}
	
}
