package com.qorder.model.interfaces;


public interface IStore {
	
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
