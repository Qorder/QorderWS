package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.productType.ProductType;

public class ProductMenu {
	
	private long id;
	private List<ProductType> prodTypeList = new ArrayList<ProductType>();


	public long getId() {
		return this.id;
	}
	

	public void setId(long id)
	{
		this.id = id;
	}


	public void addProductType(ProductType prodType) {
		this.prodTypeList.add(prodType);
	}

	public List<ProductType> getProdTypeList() {
		return prodTypeList;
	}


	public void setProdTypeList(List<ProductType> prodTypeList) {
		this.prodTypeList = prodTypeList;
	}
	
}
