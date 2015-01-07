package com.qorder.qorderws.controller;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.service.IMenuService;
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
@RequestMapping(value = "/menus")
public class MenuController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MenuController.class);
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 
	 * @param menuID
	 * @return menu transfer object to client 
	 */
	@RequestMapping(value = "/{menuID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long menuID) throws ResourceNotFoundException {
		LOGGER.info("Request for menu with id parameter equal " + menuID.toString(), menuID);
		
		MenuDTO menuDto = menuService.fetchMenuById(menuID);
		return new ResponseEntity<>(menuDto, HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{menuID}/categories", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Void> crateCategory(@PathVariable Long menuID, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
		LOGGER.info("Request for category creation with menu id equals :" + menuID);
		
		long categoryID = menuService.addCategory(menuID, categoryDTO);
		
		URI location = URI.create(EDomainLinkProvider.INSTANCE.getLocationFor(EEntity.CATEGORY) + categoryID);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> sendException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>("Exception was raised", HttpStatus.CONFLICT);
	}
}
