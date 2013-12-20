package com.qorder.qorderws.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.DetailedCategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.mapper.CategoryToCategoryInfoMapper;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	
	public MenuDTO requestForMenu(String uri, Long businessId) {
		ResponseEntity<MenuDTO> response = restTemplate.getForEntity(uri + businessId, MenuDTO.class);
		MenuDTO menuDTO = response.getBody();
		return menuDTO;
	}
	
	public DetailedCategoryDTO requestForCategory(String uri, Long categoryId) {
		ResponseEntity<DetailedCategoryDTO> response = restTemplate.getForEntity(uri + categoryId, DetailedCategoryDTO.class);
		DetailedCategoryDTO categoryDTO = response.getBody();
		return categoryDTO;
	}
	
	//FIXME: rest request not working error 405
	public void putNewCategory(String url, Long businessId, Category category) throws HttpClientErrorException {
		restTemplate.put(url + businessId, new CategoryToCategoryInfoMapper().map(category, new CategoryDTO()));
	}
	
	public void putNewBusiness(String url, Long ownerId, Business business) throws HttpClientErrorException {
		restTemplate.put(url + ownerId, business);
	}
	
	public void putNewProduct(String url, Long categoryId, Product product) throws HttpClientErrorException {
		restTemplate.put(url + categoryId, product);
	}
	

}
