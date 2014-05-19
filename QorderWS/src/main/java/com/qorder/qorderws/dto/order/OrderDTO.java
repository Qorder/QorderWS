package com.qorder.qorderws.dto.order;

import java.util.List;

import com.qorder.qorderws.dto.product.BasketProductDTO;

public class OrderDTO {
	
	private String tableNumber;
	
	private List<BasketProductDTO> orders;

	public String getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(String tableNumber) {
		this.tableNumber = tableNumber;
	}

	public List<BasketProductDTO> getOrders() {
		return orders;
	}

	public void setOrders(List<BasketProductDTO> orders) {
		this.orders = orders;
	}
	
}
