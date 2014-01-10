package com.qorder.qorderws.model.business;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "BUSINESSES")
public class Business {

	@Id
	@GeneratedValue
	@Column(name = "BUSINESS_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToOne
	@MapsId
	@Column(name="MENU_ID")
	private Menu menu;

	@OneToMany(targetEntity = Business.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "BUSINESS_ID")
	private Set<Business> childBusinesses = new HashSet<Business>();

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

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<Business> getChildBusinesses() {
		return childBusinesses;
	}

	public void setChildBusinesses(Set<Business> childBusinesses) {
		this.childBusinesses = childBusinesses;
	}

	public boolean addBusiness(Business business) {
		if (childBusinesses.add(business)) {
			business.setMenu(menu);
			return true;
		}
		return false;
	}

}
