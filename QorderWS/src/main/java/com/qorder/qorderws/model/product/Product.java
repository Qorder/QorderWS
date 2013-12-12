package com.qorder.qorderws.model.product;

import java.math.BigDecimal;
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

import com.qorder.qorderws.model.attribute.Attribute;

@Entity
@Table(name="PRODUCT")
public class Product {
	
	@Id
    @GeneratedValue
    @Column(name="PRODUCT_ID")
	private long id;
	@Column(name="NAME")
	private String name;
	@Column(name="PRICE")
	private BigDecimal price;
	
	@OneToMany(targetEntity = Attribute.class, cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinColumn(name = "PRODUCT_ID")
	private List<Attribute> AttributeList = new ArrayList<Attribute>();
	
	
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	public Product() {
		
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<Attribute> getAttributeList() {
		return AttributeList;
	}

	public void setAttributeList(List<Attribute> attributeList) {
		AttributeList = attributeList;
	}
	
	

}
