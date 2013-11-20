package com.qorder.model.interfaces;

import java.util.List;

public interface IProductMenu {
	
	public long getId();
	public void setId(long id);
	public void addProductType(IProductType prodType);
	public List<IProductType> getProductTypes();

}
