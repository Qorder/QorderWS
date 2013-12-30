package com.qorder.qorderws.dto.order;

import java.util.ArrayList;
import java.util.List;

public class OrderViewDTO {
	
	private Long id;
	
	private String tableNumber;
	
	private String dateTime;
	
	private List<ProductHolderDTO> orderedProducts = new ArrayList<ProductHolderDTO>();
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public List<ProductHolderDTO> getOrderedProducts() {
		return orderedProducts;
	}

	public void setOrderedProducts(List<ProductHolderDTO> orderedProducts) {
		this.orderedProducts = orderedProducts;
	}

	public void add(ProductHolderDTO productHolderDTO) {
		this.orderedProducts.add(productHolderDTO);
	}

}
