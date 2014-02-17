package com.qorder.qorderws.model.business;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "BUSINESSES")
public class Business extends AbstractBusiness {

	@OneToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_MENU_ID")
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

}
