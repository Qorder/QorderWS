package com.qorder.qorderws.model.business;

import com.qorder.qorderws.model.menu.ProductMenu;


public interface IBusiness {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public ProductMenu getMenu();
	public void setMenu(ProductMenu menu);
	
	//TODO: na mpei owner

}
