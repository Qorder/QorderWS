package com.qorder.qorderws.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.model.menu.Menu;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public Menu requestForMenu(String uri, long businessId) {
		ResponseEntity<Menu> responseMenu = restTemplate.getForEntity(uri, Menu.class);
		Menu menu = responseMenu.getBody();
		return menu;
	}
	

}
