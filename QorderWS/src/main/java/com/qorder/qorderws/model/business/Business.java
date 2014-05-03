package com.qorder.qorderws.model.business;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "BUSINESSES")
public class Business extends ABusiness {

	@ManyToOne(optional=false) 
	@JoinColumn(name = "FK_MENU_ID", nullable=false, updatable=false)
	private Menu menu;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentBusiness")
	private Set<ChildBusiness> childBusinesses = new HashSet<ChildBusiness>();

	@Override
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<ChildBusiness> getChildBusinesses() {
		return childBusinesses;
	}

	public void setChildBusinesses(Set<ChildBusiness> childBusinesses) {
		this.childBusinesses = childBusinesses;
	}

	public boolean addChild(ChildBusiness business) {
		if (childBusinesses.add(business)) {
			business.setParent(this);
			return true;
		}
		return false;
	}

	public boolean removeChild(ChildBusiness business) {
		return childBusinesses.remove(business);
	}

}
