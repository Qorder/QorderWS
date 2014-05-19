package com.qorder.qorderws.model.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ABusiness {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "BUSINESS_ID")
	protected Long id;

	@Column(name = "NAME")
	protected String name;

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

	public abstract Menu getMenu();
}
