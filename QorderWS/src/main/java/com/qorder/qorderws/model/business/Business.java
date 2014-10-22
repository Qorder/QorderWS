package com.qorder.qorderws.model.business;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "BUSINESSES")
public class Business {

	@Id
	@GeneratedValue
	@Column(name = "BUSINESS_ID")
	protected Long id;

	@Column(name = "NAME")
	protected String name;
	
	@OneToOne(targetEntity = Menu.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "FK_MENU_ID", nullable=false, updatable=false)
	private Menu menu;
	
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
	

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
	
}
