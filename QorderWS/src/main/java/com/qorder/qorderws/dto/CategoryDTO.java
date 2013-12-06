package com.qorder.qorderws.dto;

import java.util.List;

public class CategoryDTO {
	
	private String name;
	private List<ProductDTO> productList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ProductDTO> getProductList() {
		return productList;
	}
	public void setProductList(List<ProductDTO> productList) {
		this.productList = productList;
	}
	
}
