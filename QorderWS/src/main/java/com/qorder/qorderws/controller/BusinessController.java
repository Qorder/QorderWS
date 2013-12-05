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

import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.mapper.BusinessToBusinessInfoDTOMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.service.IBusinessService;

@Controller
@RequestMapping(value = "/menus")
public class BusinessController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);

	@Autowired
	private IBusinessService businessService;

	/**
	 * 
	 * @param businessId
	 * @return
	 */
	@RequestMapping(value = "/business", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<BusinessInfoDTO> getMenuById(@RequestParam Long id) {
		LOGGER.info(
				"Request for menu with id parameter equal " + id.toString(), id);
		Business business = businessService.fetchBusinessById(id);
		return new ResponseEntity<BusinessInfoDTO>(
				new BusinessToBusinessInfoDTOMapper().map(business,
						new BusinessInfoDTO()), HttpStatus.OK);
	}

	public IBusinessService getBusinessService() {
		return businessService;
	}

	public void setBusinessService(IBusinessService businessService) {
		this.businessService = businessService;
	}
}
