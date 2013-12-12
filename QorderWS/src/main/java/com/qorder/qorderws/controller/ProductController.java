package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.service.IProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);
	
	@Autowired
	private IProductService productService;
	
	@RequestMapping(value = "/product", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<DetailedProductDTO> getProductById(@RequestParam Long id) {
		LOGGER.info("Request for product with id parameter equal " + id.toString(), id);
		DetailedProductDTO detailedProductDTO = productService.fetchProductById(id);
		
		return new ResponseEntity<DetailedProductDTO>(detailedProductDTO, HttpStatus.OK); 
	}
}
