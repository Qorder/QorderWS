package com.qorder.qorderws.dto.order;

import java.util.ArrayList;
import java.util.List;

public class BusinessOrdersDTO {

	private List<OrderViewDTO> ordersList = new ArrayList<OrderViewDTO>();

	public List<OrderViewDTO> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<OrderViewDTO> ordersList) {
		this.ordersList = ordersList;
	}
	
	public void addOrderViewDTO(OrderViewDTO orderViewDTO) {
		this.ordersList.add(orderViewDTO);
	}

}
