package com.qorder.qorderws.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
    private ServletContext context;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageController.class);
	
	@RequestMapping(value = "/product", params = "id", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE )
	public ResponseEntity<byte[]> getProductImage(@RequestParam Long id) throws IOException, NullPointerException {
		LOGGER.info("Request for menu with id parameter equal " + id.toString(), id);
		InputStream imageStream = context.getResourceAsStream("WEB-INF/resources/images/products/" + id.toString() + ".JPG");
		byte[] imageByteArray = ByteStreams.toByteArray(imageStream);
		
		return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<String> sendException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<String>("An IOException was raised", HttpStatus.FAILED_DEPENDENCY);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> sendBadRequestException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<String>("An IOException was raised", HttpStatus.BAD_REQUEST);
	}

}
