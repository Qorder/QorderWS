package com.qorder.qorderws.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.mapper.CategoryToCategoryInfoMapper;
import com.qorder.qorderws.model.category.Category;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public MenuDTO requestForMenu(String uri, Long businessId) {
		ResponseEntity<MenuDTO> response = restTemplate.getForEntity(uri + businessId, MenuDTO.class);
		MenuDTO menu = response.getBody();
		return menu;
	}
	
	//FIXME: rest request not working error 405
	public void postNewCategory(String url, Long businessId, Category category) {
		restTemplate.put(url + businessId, new CategoryToCategoryInfoMapper().map(category, new CategoryDTO()));
	}
	

}
