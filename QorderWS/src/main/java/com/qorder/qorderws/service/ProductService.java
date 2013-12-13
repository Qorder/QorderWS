package com.qorder.qorderws.service;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.IProductDAO;
import com.qorder.qorderws.dto.DetailedProductDTO;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class ProductService implements IProductService {
	
	private IProductDAO productDAO;
	
	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@Override
	public DetailedProductDTO fetchProductById(long productId) {
		  Product product = productDAO.findById(productId);
		  return new ProductToDetailedProductDTOMapper().map(product, new DetailedProductDTO());
	}

}
