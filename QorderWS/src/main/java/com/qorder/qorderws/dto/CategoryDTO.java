package com.qorder.qorderws.dto;

public class CategoryDTO {
	
	private Long id = null;
	private String name;
	private String uri = "http://snf-185147.vm.okeanos.grnet.gr:8080/qorderws/categories/category?id=";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
	public String getUri() {
		return uri + id;
	}
	
	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
}
