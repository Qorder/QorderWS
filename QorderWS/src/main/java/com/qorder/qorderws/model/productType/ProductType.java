package com.qorder.qorderws.model.productType;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_TYPE")
public class ProductType {
	
	@Id
    @GeneratedValue
    @Column(name="PRODUCT_TYPE_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@OneToMany(mappedBy="ProductType")
	private List<Product> productList = new ArrayList<Product>();	
	
		
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
		this.name = name;
	}


	public void addProduct(Product product) {
		this.productList.add(product);
	}


	public List<Product> getProductList() {
		return productList;
	}


	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}


}
