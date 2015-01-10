package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.dto.product.ProductHolderDTO;
import com.qorder.qorderws.model.order.ProductHolder;

import javax.validation.constraints.NotNull;

public class ProductHolderToProductHolderDTOMapper implements IMapper<ProductHolder, ProductHolderDTO> {

	@Override
	public ProductHolderDTO map(@NotNull ProductHolder source, @NotNull ProductHolderDTO target) {
		ProductDTO productDTO = new ProductToProductDTOMapper().map(source.getProduct(), new ProductDTO());
		target.setProductDTO(productDTO);
		
		target.setQuantity(source.getQuantity());
		target.setAttributes(source.getSelectedAttributes());
		target.setNotes(source.getNotes());
		
		return target;
	}
	
	

}
