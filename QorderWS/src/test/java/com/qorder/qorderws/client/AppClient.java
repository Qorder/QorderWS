package com.qorder.qorderws.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.dto.BusinessInfoDTO;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public BusinessInfoDTO requestForMenu(String uri, long businessId) {
		ResponseEntity<BusinessInfoDTO> response = restTemplate.getForEntity(uri, BusinessInfoDTO.class);
		BusinessInfoDTO menu = response.getBody();
		return menu;
	}
	

}
