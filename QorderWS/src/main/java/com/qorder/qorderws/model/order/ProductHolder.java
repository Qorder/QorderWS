package com.qorder.qorderws.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_HOLDER")
public class ProductHolder {
	
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_HOLDER_ID")
	private long id;
	
	@Column(name="COMMENTS")
	private String comments;
	
	@ManyToOne(targetEntity=Product.class)
	@JoinColumn(name="PRODUCT_ID")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Product product;
	
	
	public ProductHolder() {
		super();
	}

	public ProductHolder(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
		
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
		
	}
	
}
