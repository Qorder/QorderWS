package com.qorder.qorderws.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.service.IProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/product", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedProductDTO> getProductById(@RequestParam Long id) throws ResourceNotFoundException {
		LOGGER.info("Request for product with id parameter equal " + id.toString(), id);
		return new ResponseEntity<>(productService.fetchProductById(id), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/category", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> storeProducts(@RequestParam Long id, @RequestBody DetailedProductDTO[] productsDTO) throws ResourceNotFoundException, IOException {
		LOGGER.info("Request to store products save with business id equals :" + id);
		productService.storeProducts(id, productsDTO );
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ IOException.class })
	ResponseEntity<String> sendIOException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
