package com.qorder.qorderws.model.client;

public interface IClient {
	
	public long getId();
	public void setId(long id);
	
	void setName(String name);
	
	String getName();
	
	String setSurname(String surname);
	
	String getSurname();

}
