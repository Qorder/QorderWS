package com.qorder.qorderws.controller;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.service.IBusinessService;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/businesses")
public class BusinessController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessController.class);

	private final IBusinessService businessService;

	@Autowired
	public BusinessController(IBusinessService businessService) {
		this.businessService = businessService;
	}

	@RequestMapping(value = "/owner/{ownerId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createBusiness(@PathVariable Long ownerId, @RequestBody BusinessDTO businessDTO) {
		LOGGER.info("Request for business creation");
		
		long businessID = businessService.createBusiness(businessDTO);
		
		URI location = URI.create(EDomainLinkProvider.INSTANCE.getLocationFor(EEntity.BUSINESS) + businessID);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{businessId}", method = RequestMethod.GET , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BusinessDTO> getBusiness(@PathVariable Long businessId)  throws ResourceNotFoundException {
		LOGGER.info("Request for business");
		
		BusinessDTO business = businessService.fetchBusinessByID(businessId);
		return new ResponseEntity<>(business, HttpStatus.OK);
	}
}
