package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.ProxyCategory;

public class Menu {

	private List<ProxyCategory> prodTypeList = new ArrayList<ProxyCategory>();

	public void addProductType(ProxyCategory prodType) {
		this.prodTypeList.add(prodType);
	}

	public List<ProxyCategory> getProdTypeList() {
		return prodTypeList;
	}

	public void setProdTypeList(List<ProxyCategory> prodTypeList) {
		this.prodTypeList = prodTypeList;
	}

}
