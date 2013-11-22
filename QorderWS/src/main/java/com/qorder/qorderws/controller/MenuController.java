package com.qorder.qorderws.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qorder.qorderws.model.menu.IProductMenu;
import com.qorder.qorderws.service.IMenuService;

@Controller
@RequestMapping(value = "/menus")
public class MenuController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuController.class);
	
	private IMenuService menuService;
	
	/**
	 * 
	 * @param businessId
	 * @return
	 */
	@RequestMapping(value = "/{businessId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<IProductMenu> getMenuById(@PathVariable Long businessId) {
		LOGGER.info("Request for user with id parameter equal "+ businessId.toString(), businessId);
		IProductMenu menu = menuService.fetchMenuById(businessId);
		return new ResponseEntity<IProductMenu>( menu, HttpStatus.OK);
	}



	public IMenuService getMenuService() {
		return menuService;
	}



	public void setMenuService(IMenuService menuService) {
		this.menuService = menuService;
	}
	
	//second way
	/*@RequestMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public IProductMenu getMenubyID(@PathVariable Long id) {
		LOGGER.info("Request for user with json value id parameter equal "+ id.toString(), id);
		return new ProductMenu((long) 5);
	}
	*/
}
