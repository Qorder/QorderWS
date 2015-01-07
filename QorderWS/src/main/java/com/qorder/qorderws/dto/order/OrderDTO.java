package com.qorder.qorderws.dto.order;

import com.qorder.qorderws.dto.product.BasketProductDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderDTO {
	
	private String tableNumber;
	
	private List<BasketProductDTO> orders = new ArrayList<>();

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
