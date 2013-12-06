package com.qorder.qorderws.dto;

import java.math.BigInteger;
import java.util.List;

public class ProductDTO {
	private String name;
	private BigInteger price;
	private List<String> attributes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigInteger getPrice() {
		return price;
	}
	public void setPrice(BigInteger price) {
		this.price = price;
	}
	public List<String> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}
}
