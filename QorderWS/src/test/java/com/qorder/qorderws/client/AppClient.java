package com.qorder.qorderws.client;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.mapper.CategoryToCategoryInfoMapper;
import com.qorder.qorderws.model.category.Category;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public BusinessInfoDTO requestForMenu(String uri, Long businessId) {
		ResponseEntity<BusinessInfoDTO> response = restTemplate.getForEntity(uri + businessId, BusinessInfoDTO.class);
		BusinessInfoDTO menu = response.getBody();
		return menu;
	}
	
	//FIXME: rest request not working error 405
	public void postNewCategory(String url, Long businessId, Category category) {
		restTemplate.put(url + businessId, new CategoryToCategoryInfoMapper().map(category, new CategoryInfoDTO()));
	}
	

}
