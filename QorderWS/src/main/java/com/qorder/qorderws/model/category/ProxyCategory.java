package com.qorder.qorderws.model.category;

public class ProxyCategory implements ICategory {

	private long id;
	private String name;
	private String uriToReal;

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getUriToReal() {
		return uriToReal;
	}

	public void setUriToReal(String uriToReal) {
		this.uriToReal = uriToReal;
	}

	@Override
	public String toString() {
		return "id: " + id + " name: " + name;
	}
	
	

}
