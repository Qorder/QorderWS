package com.qorder.qorderws.controller;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.io.ByteStreams;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	@RequestMapping(value = "/product", params = "id", method = RequestMethod.GET, produces = {
			MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<byte[]> getProductImage(@RequestParam Long id) throws IOException, NullPointerException {
		LOGGER.info("Request for menu with id parameter equal " + id.toString(), id);

		Resource image = new ClassPathResource("images/products/" + id.toString() + ".JPG");
		if (!image.exists()) {
			image = new ClassPathResource("images/products/defaultProduct.png"); // load default image
		}

		try (InputStream imageStream = image.getInputStream()) {
			byte[] imageByteArray = ByteStreams.toByteArray(imageStream);
			return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
		}
	}

	@ExceptionHandler({NullPointerException.class, IOException.class})
	public ResponseEntity<String> sendBadRequestException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex);
		return new ResponseEntity<>("An Exception was raised", HttpStatus.BAD_REQUEST);
	}

}
