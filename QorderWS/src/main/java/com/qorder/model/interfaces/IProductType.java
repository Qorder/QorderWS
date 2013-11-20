package com.qorder.model.interfaces;

import java.util.List;

public interface IProductType {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public void addProduct(IProduct product);
	public List<IProduct> getProductList();
	
	

}
