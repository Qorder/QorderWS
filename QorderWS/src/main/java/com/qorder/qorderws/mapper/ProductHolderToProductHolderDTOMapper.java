package com.qorder.qorderws.mapper;

import com.qorder.qorderws.dto.order.ProductHolderDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.model.order.ProductHolder;

public class ProductHolderToProductHolderDTOMapper implements IMapper<ProductHolder, ProductHolderDTO> {

	@Override
	public ProductHolderDTO map(ProductHolder source, ProductHolderDTO target) {
		target.setNotes(source.getNotes());
		ProductDTO productDTO = new ProductToProductDTOMapper().map(source.getProduct(), new ProductDTO());
		target.setProductDTO(productDTO);
		target.setQuantity(source.getQuantity());
		return target;
	}
	
	

}
