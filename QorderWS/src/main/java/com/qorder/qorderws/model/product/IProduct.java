package com.qorder.qorderws.model.product;

import java.math.BigDecimal;
import java.util.List;

public interface IProduct {

	long getId();

	void setId(long id);

	String getName();

	void setName(String name);

	BigDecimal getPrice();

	void setPrice(BigDecimal price);

	List<String> getAttributes();

	void addAttribute(String attribute);

}
