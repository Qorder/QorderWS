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

import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.service.IMenuService;

@Controller
@RequestMapping(value = "/menus")
public class MenuController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);

	@Autowired
	private IMenuService menuService;

	/**
	 * 
	 * @param businessId
	 * @return
	 */
	@RequestMapping(value = "/business", params = "id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Menu> getMenuById(@RequestParam Long id) {
		LOGGER.info(
				"Request for user with id parameter equal " + id.toString(), id);
		Menu menu = menuService.fetchMenuById(id);
		return new ResponseEntity<Menu>(menu, HttpStatus.OK);
	}

	public IMenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
}
