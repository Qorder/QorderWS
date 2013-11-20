package com.qorder.model;

import java.math.BigDecimal;
import java.util.List;

public interface IProduct {
	
	public int getId();
	public String getName();
	public void setName(String name);
	public BigDecimal getPrice();
	public void setPrice(BigDecimal price);
	public List<String> getAttributes();
	public void addAttribute(String attribute);
	
	
}
