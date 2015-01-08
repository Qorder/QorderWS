package com.qorder.qorderws.controller;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.service.IImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value ="/images")
public class ImageController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);

	private final IImageService imageService;

	@Autowired
	public ImageController(IImageService imageService) {
		this.imageService = imageService;
	}

	@RequestMapping(value = "/product/{productID}", method = RequestMethod.GET,
			produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public ResponseEntity<byte[]> getProductImage(@PathVariable long productID) throws IOException, NullPointerException {
		LOGGER.info("Request for menu with id parameter equal " + productID, productID);
		
		byte[] imageByteArray = imageService.getImageFor(EEntity.PRODUCT_IMAGE, productID);
		return new ResponseEntity<>(imageByteArray, HttpStatus.OK);
	}

	@ExceptionHandler({NullPointerException.class, IOException.class})
	public ResponseEntity<String> sendBadRequestException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex);
		return new ResponseEntity<>("An Exception was raised", HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
