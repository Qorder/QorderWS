package com.qorder.qorderws.dto.order;

import java.util.ArrayList;
import java.util.List;

public class BusinessOrdersDTO {

	private List<OrderViewDTO> orders = new ArrayList<OrderViewDTO>();
	
	public List<OrderViewDTO> getOrders() {
		return orders;
	}



	public void setOrders(List<OrderViewDTO> orders) {
		this.orders = orders;
	}



	public void addOrderViewDTO(OrderViewDTO orderViewDTO) {
		this.orders.add(orderViewDTO);
	}

}
