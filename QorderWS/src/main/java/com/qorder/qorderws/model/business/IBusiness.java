package com.qorder.qorderws.model.business;

import com.qorder.qorderws.model.menu.IProductMenu;


public interface IBusiness {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public String getAddress();
	public void setAddress(String address);
	public IProductMenu getMenu();
	public void setMenu(IProductMenu menu);
	
	//TODO: na mpei owner

}
