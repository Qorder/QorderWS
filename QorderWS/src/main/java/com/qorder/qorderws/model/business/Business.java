package com.qorder.qorderws.model.business;

import com.qorder.qorderws.model.menu.IProductMenu;

public class Business implements IBusiness {
	
	private long id;
	private String name;
	private String address;
	private IProductMenu menu;

	public Business(long id) {
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
		this.name=name;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public void setAddress(String address) {
		this.address=address;
	}
	
	@Override
	public void setMenu(IProductMenu menu) {
		this.menu = menu;
	}
	
	@Override
	public IProductMenu getMenu() {
		return menu;
	}


}
