package com.qorder.qorderws.dto.product;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

import java.math.BigDecimal;

public class ProductDTO {

	private long id;

	private String name;

	private BigDecimal price;

	private final String href = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.PRODUCT);


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
	
	public void setPrice(BigDecimal bigDecimal) {
		this.price = bigDecimal;
	}

	public String getHref() {
		return href + id;
	}
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
}
