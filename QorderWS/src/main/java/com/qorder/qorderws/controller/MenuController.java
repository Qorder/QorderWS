package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.service.IMenuService;

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
	@Transactional(readOnly = true)
	@RequestMapping(value = "/{menuID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MenuDTO> getMenuById(@PathVariable Long menuID) throws ResourceNotFoundException {
		LOGGER.info("Request for menu with id parameter equal " + menuID.toString(), menuID);
		
		MenuDTO menuDto = menuService.fetchMenuById(menuID);
		return new ResponseEntity<>(menuDto, HttpStatus.OK);
	}

	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> sendException(Exception ex) {
		LOGGER.warn("Exception was thrown, with cause " + ex.getCause() + "\nMessage: " + ex.getLocalizedMessage(), ex );
		return new ResponseEntity<>("Exception was raised", HttpStatus.CONFLICT);
	}
}
