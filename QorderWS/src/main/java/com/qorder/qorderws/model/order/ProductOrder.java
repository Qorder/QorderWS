package com.qorder.qorderws.model.order;

import com.qorder.qorderws.model.product.IProduct;

public class ProductOrder implements IProductOrder {
	
	private int quantity;
	private String comments;
	private IProduct product;
	
	
	public ProductOrder(int quantity, IProduct product) {
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
