package com.qorder.qorderws.dto;

public class BusinessDTO {
	
	private Long id;
	private String name;
	private MenuDTO menu;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MenuDTO getMenu() {
		return menu;
	}
	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}
	
}
