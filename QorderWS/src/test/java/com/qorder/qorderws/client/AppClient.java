package com.qorder.qorderws.client;

import com.qorder.qorderws.dto.BusinessDTO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.dto.order.OrderDTO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AppClient {
	
	private RestTemplate restTemplate = new RestTemplate();
	private EDomainLinkProvider refProvider = EDomainLinkProvider.INSTANCE;
	
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
	public void putNewCategory(String url, Long businessId, CategoryDTO category) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + businessId, category);
	}
	
	public void postNewBusiness(long ownerId, BusinessDTO business) throws HttpClientErrorException {
		restTemplate.postForLocation(refProvider.getHttpPathFor(EEntity.BUSINESS) + "owner/" + ownerId, business);
	}
	
	public void postNewProducts(String url, Long categoryId, List<DetailedProductDTO> products) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + categoryId, products);
	}
	
	public void putNewOrder(String url, Long businessId, OrderDTO orderDTO) throws HttpClientErrorException {
		restTemplate.put(refProvider.getHost() + url + businessId, orderDTO);
	}

}
