package com.qorder.qorderws.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.menu.ProductMenu;
import com.qorder.qorderws.model.productType.IProductType;
import com.qorder.qorderws.model.productType.ProductType;

@Entity
@Table(name="BUSINESS")
public class Business implements IBusiness {
	
	@Id
	@GeneratedValue
	@Column(name="BUSINESS_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	
	@OneToMany(targetEntity=ProductType.class, cascade = CascadeType.ALL)
	@JoinTable(name = "BUSSINES_PRODUCT_TYPE", joinColumns = { @JoinColumn(name = "BUSINESS_ID") }, inverseJoinColumns = { @JoinColumn(name = "PRODUCT_TYPE_ID") })
	private List<IProductType> productTypes = new ArrayList<IProductType>();

	public Business(String name) {
		this.name = name;
	}

	public long getId() {
		return this.id;
	}
	

	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name=name;
	}

	@Override
	public ProductMenu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenu(ProductMenu menu) {
		// TODO Auto-generated method stub
		
	}



}
