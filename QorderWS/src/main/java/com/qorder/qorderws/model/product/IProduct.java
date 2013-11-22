package com.qorder.qorderws.model.product;

import java.math.BigDecimal;
import java.util.List;

public interface IProduct {
	
	public long getId();
	public void setId(long id);
	public String getName();
	public void setName(String name);
	public BigDecimal getPrice();
	public void setPrice(BigDecimal price);
	public List<String> getAttributes();
	public void addAttribute(String attribute);
	
	
}
