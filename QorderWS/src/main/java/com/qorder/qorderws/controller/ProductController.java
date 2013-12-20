package com.qorder.qorderws.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.exception.ProductDoesNotExistException;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.service.IProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);
	
	@Autowired
	private IProductService productService;

	
	@RequestMapping(value = "/product", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedProductDTO> getProductById(@RequestParam Long id) throws ProductDoesNotExistException {
		LOGGER.info("Request for product with id parameter equal " + id.toString(), id);
		return new ResponseEntity<DetailedProductDTO>(productService.fetchProductById(id), HttpStatus.OK); 
	}
	
	@RequestMapping(value = "/category", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> crateCategory(@RequestParam Long id, @RequestBody Product product) throws BusinessDoesNotExistException, CategoryDoesNotExistException, IOException {
		LOGGER.info("Request for category creation with business id equals :" + id);
		productService.createProduct(id, product);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ExceptionHandler({ CategoryDoesNotExistException.class, BusinessDoesNotExistException.class})
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler({ IOException.class })
	ResponseEntity<String> sendIOException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public IProductService getProductService() {
		return productService;
	}

	public void setProductService(IProductService productService) {
		this.productService = productService;
	}
	
	
}
