package com.qorder.qorderws.dto.product;

import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

import java.math.BigDecimal;

public class ProductDTO {
	
	private Long id;
	private String name;
	private BigDecimal price;
	private final String productRequestURI = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.PRODUCT);
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getProductRequestUri() {
		return productRequestURI + id;
	}
	
	@Override
	public String toString() {
		return name + " id " + id;
	}
	
}
