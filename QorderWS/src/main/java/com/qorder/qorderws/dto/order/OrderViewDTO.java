package com.qorder.qorderws.dto.order;

import java.util.ArrayList;
import java.util.List;

public class OrderViewDTO {
	
	private Long id;
	
	private String tableNumber;
	
	private String dateTime;
	
	private List<ProductHolderDTO> orderList = new ArrayList<ProductHolderDTO>();
	
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

	public List<ProductHolderDTO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<ProductHolderDTO> orderList) {
		this.orderList = orderList;
	}

	public void add(ProductHolderDTO productHolderDTO) {
		this.orderList.add(productHolderDTO);
	}

}
