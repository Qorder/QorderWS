package com.qorder.qorderws.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	@OneToMany(targetEntity=ProductType.class)
	@JoinColumn(name="BUSINESS_ID")
	private List<IProductType> productTypeList= new ArrayList<IProductType>();	
	
	
	public Business(String name) {
		this.name = name;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}


	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
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

	@Override
	public java.util.List<IProductType> getProductTypeList() {
		return this.productTypeList;
	}

	@Override
	public void setProductTypeList(java.util.List<IProductType> productTypeList) {
		this.productTypeList=productTypeList;
	}

	@Override
	public void addProductType(IProductType productType) {
		this.productTypeList.add(productType);
		
	}



}
