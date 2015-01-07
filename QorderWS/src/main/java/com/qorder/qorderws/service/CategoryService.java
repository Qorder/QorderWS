package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.DetailedProductDTOtoProductMapper;
import com.qorder.qorderws.mapper.ProductToProductDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CategoryService implements ICategoryService {

	private final ICategoryRepository categoryRepository;

	@Autowired
	public CategoryService(ICategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<ProductDTO> fetchCategoryByID(long categoryId) throws ResourceNotFoundException {
		Category fetchedCategory = categoryRepository.findOne(categoryId);
		
		ProductToProductDTOMapper mapper = new ProductToProductDTOMapper();
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		fetchedCategory.getProductList().forEach((product) -> {
			productList.add(mapper.map(product, new ProductDTO()));
		} );
		return productList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long addProduct(long categoryID, DetailedProductDTO productDTO) throws ResourceNotFoundException {
		Product product = new DetailedProductDTOtoProductMapper().map(productDTO,new Product());
		
		Category category = categoryRepository.findOne(categoryID);
		category.addProduct(product);

		categoryRepository.save(category);
		return product.getId();
	}
}
