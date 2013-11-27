package com.qorder.qorderws.model.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.qorder.qorderws.model.product.IProduct;
import com.qorder.qorderws.model.product.Product;

@Entity
@Table(name="PRODUCT_ORDER")
public class ProductOrder implements IProductOrder{
	
	@Id
	@GeneratedValue
	@Column(name="PRODUCT_ORDER_ID")
	private long id;
	@Column(name="COMMENTS")
	private String comments;
	@ManyToOne(targetEntity=Product.class)
	@JoinColumn(name="PRODUCT_ID")
	private IProduct product;
	@ManyToOne
	@JoinColumn(name="ORDER_LIST_ID")
	private OrderList orderList;
	
	
	public ProductOrder(IProduct product) {
		this.product = product;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public IProduct getProduct() {
		return product;
	}


	public void setProduct(IProduct product) {
		this.product = product;
	}


	public ProductOrder(long id) {
		super();
		this.id = id;
	}


	
	

}
