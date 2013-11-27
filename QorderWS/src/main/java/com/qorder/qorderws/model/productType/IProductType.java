package com.qorder.qorderws.model.productType;

import java.util.List;

import com.qorder.qorderws.model.product.IProduct;

public interface IProductType {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public void addProduct(IProduct product);
	public List<IProduct> getProductList();
	void setProductList(List<IProduct> productList);
	
	

}
