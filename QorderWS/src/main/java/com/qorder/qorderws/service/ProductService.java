package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.ProductToDetailedProductDTOMapper;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Transactional
public class ProductService implements IProductService {

	private final IProductRepository productRepository;

	@Autowired
	public ProductService(IProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public DetailedProductDTO fetchProductById(long productId) throws ResourceNotFoundException {
		Product product = productRepository.findOne(productId);
		return Objects.nonNull(product) ?
				new ProductToDetailedProductDTOMapper()
						.map(product, new DetailedProductDTO()) : new DetailedProductDTO();
	}
	
}
