package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.repository.IProductDAO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService implements IProductService {
	
	private IProductDAO productDAO;

	@Transactional(readOnly = true)
	@Override
	public DetailedProductDTO fetchProductById(long productId) throws ResourceNotFoundException {
		Product product = productDAO.findById(productId);
		return new ProductToDetailedProductDTOMapper().map(product, new DetailedProductDTO());
	}

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}
	
}
