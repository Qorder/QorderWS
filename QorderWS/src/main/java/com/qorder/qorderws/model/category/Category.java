package com.qorder.qorderws.model.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name = "PRODUCT_CATEGORY")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "PRODUCT_CATEGORY_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(targetEntity = Product.class,cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "PRODUCT_CATEGORY_ID")
	private List<Product> productList = new ArrayList<Product>();

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

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	public boolean addProduct(Product product) {
		return productList.add(product);
	}
}
