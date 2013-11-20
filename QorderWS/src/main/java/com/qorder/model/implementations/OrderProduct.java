package com.qorder.model.implementations;

import com.qorder.model.interfaces.IOrderProduct;
import com.qorder.model.interfaces.IProduct;

public class OrderProduct implements IOrderProduct {
	
	private int quantity;
	private String comments;
	private IProduct product;
	
	
	public OrderProduct(int quantity, IProduct product) {
		this.quantity = quantity;
		this.product = product;
	}

	@Override
	public int getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String getComments() {
		return comments;
	}

	@Override
	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public IProduct getProduct() {
		return product;
	}

	@Override
	public void setProduct(IProduct product) {
		this.product = product;
	}
	
	
	

}
