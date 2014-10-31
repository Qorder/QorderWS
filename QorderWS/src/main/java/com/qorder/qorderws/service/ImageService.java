package com.qorder.qorderws.service;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.common.io.ByteStreams;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.ESystemLinkProvider;

public class ImageService implements IImageService {

	@Override
	public byte[] getImageFor(EEntity entity, long entityID) throws IOException {
		String imageName = entityID + ".jpg";
		Resource image = new ClassPathResource(ESystemLinkProvider.INSTANCE.getDirectoryFor(entity) + imageName);
		if (!image.exists()) {
			image = new ClassPathResource(ESystemLinkProvider.INSTANCE.getDefaultPathFor(entity)); // load default image
		}
		
		try (InputStream imageStream = image.getInputStream()) {
			 return ByteStreams.toByteArray(imageStream);
		}
	}

}
