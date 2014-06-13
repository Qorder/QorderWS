package com.qorder.qorderws.controller;

import java.net.URI;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.service.IBusinessService;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

@RestController
@RequestMapping(value = "/businesses")
public class BusinessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

	@Autowired
	private IBusinessService businessService;
	
	@RequestMapping(value = "/business/owner/{ownerId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createBusiness(@PathVariable UUID ownerId, @RequestBody BusinessDTO businessDTO) {
		LOGGER.info("Request for business creation");
		
		businessService.createBusiness(businessDTO);
		
		HttpHeaders headers = new HttpHeaders();
		URI location = URI.create(ReferenceProvider.INSTANCE.getLocationFor("business") + businessDTO.getId()); 
		headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/business", params = "id", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDTO> getBusiness(@RequestParam Long id)  throws ResourceNotFoundException {
		LOGGER.info("Request for business");
		
		BusinessDTO business = businessService.fetchBusinessByID(id);
		return new ResponseEntity<>(business, HttpStatus.OK);
	}
}
