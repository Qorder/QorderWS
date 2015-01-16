package com.qorder.qorderws.dto.order;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class OrderInfoDTO {

	private long id;

	private String tableNumber;
	
	private String dateTime;

	private String totalPrice;
	
	private String status;


	private final String productsRef = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.ORDER);

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

	public void setId(long id) {
		this.id = id;
	}

	public String getProductsRef() {
		return productsRef + id + "/products";
	}
}
