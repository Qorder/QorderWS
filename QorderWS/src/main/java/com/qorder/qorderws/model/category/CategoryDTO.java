package com.qorder.qorderws.model.category;

/**
 * This class is an abstracted representation of
 * our business logic category class. It's instances 
 * as data transfer objects, are usefull for informing our
 * clients about requested business's menu categories. 
 *  
 * @author Grigorios
 */
public class CategoryDTO {
	
	private long id;
	private String name;
	
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}
