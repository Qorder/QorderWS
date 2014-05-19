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

	@ManyToOne(fetch=FetchType.LAZY, optional=false) 
	@JoinColumn(name = "FK_MENU_ID", nullable=false, updatable=false)
	private Menu menu;

	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY, mappedBy = "parentBusiness")
	private Set<BranchBusiness> branchBusinesses = new HashSet<BranchBusiness>();

	@Override
	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Set<BranchBusiness> getBranchBusinesses() {
		return branchBusinesses;
	}

	public void setBranchBusinesses(Set<BranchBusiness> childBusinesses) {
		this.branchBusinesses = childBusinesses;
	}

	public boolean addBranch(BranchBusiness business) {
		if (branchBusinesses.add(business)) {
			business.setParent(this);
			return true;
		}
		return false;
	}

	public boolean removeChild(BranchBusiness business) {
		return branchBusinesses.remove(business);
	}

}
