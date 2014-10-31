package com.qorder.qorderws.fao;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.el.PropertyNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.google.common.io.ByteStreams;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.ESystemPathProvider;

public final class ImageFAO implements IFileAccessObject {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageFAO.class);
	
	private final ESystemPathProvider pathProvider = ESystemPathProvider.INSTANCE;

	@Override
	public File getFile(String path) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public byte[] getResourceByteArray(String path) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public byte[] getResourceByteArrayFor(EEntity entity, long id) throws IOException {
		byte[] imageByteArray = null;
		try{
			final String imageName = id + ".png";
			Resource image = new ClassPathResource(pathProvider.getDirectoryFor(entity) + imageName);
			if (!image.exists()) {
				image = new ClassPathResource(pathProvider.getDefaultPathFor(entity)); // load default image
			}
			
			try (InputStream imageStream = image.getInputStream()) {
				imageByteArray = ByteStreams.toByteArray(imageStream);
			}
		}
		catch(PropertyNotFoundException ex) {
			LOGGER.warn(ex.getMessage());
			imageByteArray = new byte[0];
		}
		return imageByteArray;
	}

}
