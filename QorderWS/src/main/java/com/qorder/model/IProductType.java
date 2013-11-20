package com.qorder.model;

import java.util.List;

public interface IProductType {
	
	public int getId();
	public String getName();
	public void setName();
	public void addProduct(IProduct product);
	public List<IProduct> getProductList();
	
	

}
