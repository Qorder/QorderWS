package com.qorder.qorderws.model.productType;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.product.IProduct;

public class ProductType {

	private long id;
	private String name;
	private List<IProduct> ProdList = new ArrayList<IProduct>();	
	
		
	public long getId() {
		return this.id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return this.name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void addProduct(IProduct product) {
		this.ProdList.add(product);
	}


	public List<IProduct> getProductList() {
		return this.ProdList;
	}

}
