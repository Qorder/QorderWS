package com.qorder.qorderws.dto.category;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.dto.product.ProductDTO;

public class DetailedCategoryDTO {
	
	private String name;
	private List<ProductDTO> productList = new ArrayList<ProductDTO>();
	
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
	
	public boolean addProductDTO(ProductDTO product) {
		return productList.add(product);
	}
	
}
