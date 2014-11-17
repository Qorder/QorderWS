package com.qorder.qorderws.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.dto.product.ProductHolderDTO;

public class OrderViewDTO {
	
	private Long id;
	
	private String tableNumber;
	
	private String dateTime;
	
	private String totalPrice;
	
	private String status;
	
	private List<ProductHolderDTO> orderedProducts = new ArrayList<>();
	
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

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String orderStatus) {
		this.status = orderStatus;
	}

}
