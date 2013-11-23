package com.qorder.qorderws.model.product;

import java.math.BigDecimal;
import java.util.List;

public class Product implements IProduct{
	
	private long id;
	private String name;
	private BigDecimal price;
	private List<String> attributes;
	
	
	public Product(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	@Override
	public long getId() {
		return id;
	}
	
	@Override
	public void setId(long id) {
		this.id = id;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public BigDecimal getPrice() {
		return price;
	}
	
	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@Override
	public List<String> getAttributes() {
		return attributes;
	}
	
	@Override
	public void addAttribute(String attribute) {
		this.attributes.add(attribute);
	}
	
	

}
