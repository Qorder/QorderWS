package com.qorder.model.implementations;

import java.util.ArrayList;
import java.util.List;

import com.qorder.model.interfaces.IProduct;
import com.qorder.model.interfaces.IProductType;

public class ProductType implements IProductType {

	private long id;
	private String name;
	private List<IProduct> ProdList = new ArrayList<IProduct>();	
	
	public ProductType(long id) {
		super();
		this.id = id;
	}
	
	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void addProduct(IProduct product) {
		this.ProdList.add(product);
	}

	@Override
	public List<IProduct> getProductList() {
		return this.ProdList;
	}

}
