package com.qorder.qorderws.model.order;

import com.qorder.qorderws.model.product.IProduct;

public interface IProductHolder {

	IProduct getProduct();

	void setProduct(IProduct product);

	String getComments();

	void setComments(String comments);
}