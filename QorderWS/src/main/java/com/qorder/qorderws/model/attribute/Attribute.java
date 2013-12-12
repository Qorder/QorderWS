package com.qorder.qorderws.model.attribute;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ATTRIBUTE")
public class Attribute {
	
	@Id
	@GeneratedValue
	@Column(name = "ATTRIBUTE_ID")
	private long id;

	@Column(name = "DESCRIPTION")
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
