package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
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
	
	@ExceptionHandler(BusinessDoesNotExistException.class)
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
}
