package com.qorder.qorderws.model.order;

import com.qorder.qorderws.model.product.IProduct;

public class ProductOrder implements IProductOrder {
	
	private String comments;
	private IProduct product;
	
	
	public ProductOrder(IProduct product) {
		this.product = product;
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
