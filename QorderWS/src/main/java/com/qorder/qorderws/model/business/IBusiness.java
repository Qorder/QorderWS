package com.qorder.qorderws.model.business;

import java.util.List;

import com.qorder.qorderws.model.menu.ProductMenu;
import com.qorder.qorderws.model.product.IProduct;
import com.qorder.qorderws.model.productType.IProductType;


public interface IBusiness {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public ProductMenu getMenu();
	public void setMenu(ProductMenu menu);
	public List<IProductType> getProductTypeList();
	void setProductTypeList(List<IProductType> productTypeList);
	public void addProductType(IProductType productType);
	
	//TODO: na mpei owner

}
