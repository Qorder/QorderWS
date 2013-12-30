package com.qorder.qorderws.dto.order;


public class BasketProductDTO {
	
	private long productId;
	
	private String notes;

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
}
