package com.qorder.qorderws.model.business;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "CHILDBUSINESSES")
public class ChildBusiness extends ABusiness {
	
	@ManyToOne(optional=false)
	@JoinColumn(name="FK_BUSINESS", nullable=false)
	Business parentBusiness;

	public Business getParent() {
		return parentBusiness;
	}

	public void setParent(Business parentBusiness) {
		this.parentBusiness = parentBusiness;
	}

	@Override
	public Menu getMenu() {
		return parentBusiness.getMenu();
	}

}
