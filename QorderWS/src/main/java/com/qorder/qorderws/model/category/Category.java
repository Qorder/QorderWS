package com.qorder.qorderws.model.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.IProduct;
import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name = "PRODUCT_TYPE")
public class Category implements ICategory {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_TYPE_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(targetEntity = Product.class)
	@JoinColumn(name = "PRODUCT_TYPE_ID")
	private List<IProduct> productList = new ArrayList<IProduct>();

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

	public void addProduct(IProduct product) {
		this.productList.add(product);
	}

	public void setProductList(List<IProduct> productList) {
		this.productList = productList;
	}

	public List<IProduct> getProductList() {
		return this.productList;
	}

}
