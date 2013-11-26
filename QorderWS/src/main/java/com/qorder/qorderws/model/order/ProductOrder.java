package com.qorder.qorderws.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_ORDER")
public class ProductOrder {
	
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ORDER_ID")
	private long id;
	@Column(name="COMMENTS")
	private String comments;
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID")
	private Product product;
	@ManyToOne
	@JoinColumn(name="ORDER_LIST_ID")
	private OrderList orderList;
	
	
	public ProductOrder(Product product) {
		this.product = product;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Product getProduct() {
		return product;
	}


	public void setProduct(Product product) {
		this.product = product;
	}


	public ProductOrder(long id) {
		super();
		this.id = id;
	}
	
	

}
