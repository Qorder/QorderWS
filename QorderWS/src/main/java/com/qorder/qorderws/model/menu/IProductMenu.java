package com.qorder.qorderws.model.menu;

import java.util.List;

import com.qorder.qorderws.model.productType.IProductType;

public interface IProductMenu {
	
	public long getId();
	public void setId(long id);
	public void addProductType(IProductType prodType);
	public List<IProductType> getProductTypes();

}
