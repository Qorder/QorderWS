package com.qorder.qorderws.model.business;

import com.qorder.qorderws.model.menu.ProductMenu;

public class Business implements IBusiness {
	
	private long id;
	private String name;
	private ProductMenu menu;

	public Business(String name) {
		this.name = name;
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
	public void setMenu(ProductMenu menu) {
		this.menu = menu;
	}
	
	@Override
	public ProductMenu getMenu() {
		return menu;
	}


}
