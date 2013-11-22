package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.productType.IProductType;

public class ProductMenu implements IProductMenu {
	
	private long id;
	private List<IProductType> prodTypeList = new ArrayList<IProductType>();

	public ProductMenu(long id) {
		super();
		this.id = id;
	}

	@Override
	public long getId() {
		return this.id;
	}
	
	@Override
	public void setId(long id)
	{
		this.id = id;
	}

	@Override
	public void addProductType(IProductType prodType) {
		this.prodTypeList.add(prodType);
	}

	@Override
	public List<IProductType> getProductTypes() {
		return this.prodTypeList;
	}

}
