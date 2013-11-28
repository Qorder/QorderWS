package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.ProductCategory;

public class Menu {

	private List<ProductCategory> prodTypeList = new ArrayList<ProductCategory>();

	public void addProductType(ProductCategory prodType) {
		this.prodTypeList.add(prodType);
	}

	public List<ProductCategory> getProdTypeList() {
		return prodTypeList;
	}

	public void setProdTypeList(List<ProductCategory> prodTypeList) {
		this.prodTypeList = prodTypeList;
	}

}
