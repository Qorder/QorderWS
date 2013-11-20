package com.qorder.model.implementations;

import com.qorder.model.interfaces.IProductMenu;
import com.qorder.model.interfaces.IStore;

public class Store implements IStore {
	
	private long id;
	private String name;
	private String address;
	private IProductMenu menu;

	public Store(long id) {
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
