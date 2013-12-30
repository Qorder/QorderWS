package com.qorder.qorderws.model.client;

public interface IClient {
	
	long getId();
	
	void setId(long id);
	
	void setName(String name);
	
	String getName();
	
	String setSurname(String surname);
	
	String getSurname();

}
