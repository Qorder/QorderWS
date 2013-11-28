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

import com.qorder.qorderws.model.category.ICategory;
import com.qorder.qorderws.model.category.ProductCategory;
import com.qorder.qorderws.model.menu.Menu;

@Entity
@Table(name = "BUSINESS")
public class Business implements IBusiness {

	@Id
	@GeneratedValue
	@Column(name = "BUSINESS_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(targetEntity = ProductCategory.class)
	@JoinColumn(name = "BUSINESS_ID")
	private List<ICategory> productTypeList = new ArrayList<ICategory>();

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
		this.name = name;
	}

	@Override
	public Menu getMenu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenu(Menu menu) {
		// TODO Auto-generated method stub

	}

	@Override
	public java.util.List<ICategory> getProductTypeList() {
		return this.productTypeList;
	}

	@Override
	public void setProductTypeList(java.util.List<ICategory> productTypeList) {
		this.productTypeList = productTypeList;
	}

	@Override
	public void addProductType(ICategory productType) {
		this.productTypeList.add(productType);
	}

}
