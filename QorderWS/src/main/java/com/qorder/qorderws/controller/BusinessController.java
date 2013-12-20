package com.qorder.qorderws.controller;

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

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.service.IBusinessService;

@Controller
@RequestMapping(value = "/businesses")
public class BusinessController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);

	@Autowired
	private IBusinessService businessService;

	
	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
	
	@RequestMapping(value = "/owner", params = "id", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createBusiness(@RequestParam Long id, @RequestBody Business business) {
		LOGGER.info("request for business creation with name: " + business.getName() + " from owner with id equals: " +id, id );
		businessService.createBusiness(business);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@ExceptionHandler(BusinessDoesNotExistException.class)
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		LOGGER.info("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
