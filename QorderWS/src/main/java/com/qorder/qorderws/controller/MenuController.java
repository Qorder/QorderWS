package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.service.IMenuService;

@Controller
@RequestMapping(value = "/menus")
public class MenuController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(BusinessController.class);
	
	@Autowired
	private IMenuService menuService;
	
	/**
	 * 
	 * @param businessId
	 * @return
	 * @throws BusinessDoesNotExistException
	 */
	@RequestMapping(value = "/business", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<MenuDTO> getMenuById(@RequestParam Long id) throws BusinessDoesNotExistException {
		LOGGER.info("Request for menu with id parameter equal " + id.toString(), id);
		MenuDTO menuDto = menuService.getMenuByBusinessId(id);
		return new ResponseEntity<MenuDTO>(menuDto, HttpStatus.OK);
	}
	
	@ExceptionHandler(BusinessDoesNotExistException.class)
	ResponseEntity<String> sendNotFoundException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
	}
	
}
