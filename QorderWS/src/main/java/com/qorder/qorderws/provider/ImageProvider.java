package com.qorder.qorderws.provider;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.ESystemPathProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javax.el.PropertyNotFoundException;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
public final class ImageProvider implements IFileProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageProvider.class);
	
	private final ESystemPathProvider pathProvider = ESystemPathProvider.INSTANCE;

	@Override
	public File getFile(@NotNull String path) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public byte[] getResourceByteArray(@NotNull String path) {
		throw new UnsupportedOperationException("Not implemented");
	}

	@Override
	public byte[] getResourceByteArrayFor(@NotNull EEntity entity, long id) {
		try {
			final String imageName = id + ".png";
			Resource image = new ClassPathResource(pathProvider.getDirectoryPathFor(entity) + imageName);
			if (!image.exists()) {
				image = new ClassPathResource(pathProvider.getDefaultPathFor(entity)); // load default image
			}
			return Files.readAllBytes(image.getFile().toPath());
		} catch (PropertyNotFoundException | IOException ex) {
			ex.printStackTrace();
			LOGGER.warn(ex.getMessage());
			return new byte[0];
		}
	}
}
