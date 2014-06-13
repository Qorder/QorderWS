package com.qorder.qorderws.client;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.mapper.CategoryToCategoryDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.utils.providers.ReferenceProvider;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	private ReferenceProvider refProvider = ReferenceProvider.INSTANCE;
	
	public MenuDTO requestForMenu(String uri, Long menuId) {
		ResponseEntity<MenuDTO> response = restTemplate.getForEntity(refProvider.getHost() + uri + menuId, MenuDTO.class);
		MenuDTO menuDTO = response.getBody();
		return menuDTO;
	}
	
	public ProductDTO[] requestForCategory(String uri, Long categoryId) {
		ResponseEntity<ProductDTO[]> response = restTemplate.getForEntity(refProvider.getHost() + uri + categoryId, ProductDTO[].class);
		ProductDTO[] categoryDTO = response.getBody();
		return categoryDTO;
	}
	
	//FIXME: rest request not working error 405
	public void putNewCategory(String url, Long businessId, Category category) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + businessId, new CategoryToCategoryDTOMapper().map(category, new CategoryDTO()));
	}
	
	public void putNewBusiness(String url, Long ownerId, BusinessDTO business) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + ownerId, business);
	}
	
	public void postNewProducts(String url, Long categoryId, List<DetailedProductDTO> products) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + categoryId, products);
	}
	
	public void putNewOrder(String url, Long businessId, OrderDTO orderDTO) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + businessId, orderDTO);
	}

}
