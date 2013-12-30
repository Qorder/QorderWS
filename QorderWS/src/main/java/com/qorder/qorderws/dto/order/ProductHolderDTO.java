package com.qorder.qorderws.dto.order;

import com.qorder.qorderws.dto.product.ProductDTO;


public class ProductHolderDTO {
	
	private ProductDTO productDTO;
	
	private String notes;

	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	

}
