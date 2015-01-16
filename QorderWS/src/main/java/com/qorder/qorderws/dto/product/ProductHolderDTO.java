package com.qorder.qorderws.dto.product;


import com.qorder.qorderws.model.EEntity;
import com.qorder.qorderws.utils.providers.EDomainLinkProvider;

public class ProductHolderDTO {

	private long id;

	private String name;
	
	private int quantity;
	
	private String attributes;
	
	private String notes;

	private final String href = EDomainLinkProvider.INSTANCE.getHttpPathFor(EEntity.PRODUCT);


	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
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

	public String getHref() {
		return href + id;
	}
}
