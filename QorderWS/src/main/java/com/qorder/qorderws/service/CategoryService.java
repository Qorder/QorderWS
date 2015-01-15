package com.qorder.qorderws.service;

import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.mapper.IMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;
import com.qorder.qorderws.repository.ICategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CategoryService implements ICategoryService {

	private final ICategoryRepository categoryRepository;

	private final IMapper mapper;

	@Autowired
	public CategoryService(ICategoryRepository categoryRepository, IMapper mapper) {
		this.categoryRepository = categoryRepository;
		this.mapper = mapper;
	}

	@Transactional(readOnly = true)
	@Override
	public Collection<ProductDTO> fetchCategoryByID(long categoryId) {
		Category fetchedCategory = categoryRepository.findOne(categoryId);

		List<ProductDTO> productList = new ArrayList<>();
		if(Objects.nonNull(fetchedCategory)) {
			fetchedCategory.getProductList().forEach((product) -> {
				productList.add(mapper.map(product, new ProductDTO()));
			});
		}
		return productList;
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long addProduct(long categoryID, @NotNull DetailedProductDTO productDTO) {
		Product product = mapper.map(productDTO,new Product());
		
		Category category = categoryRepository.findOne(categoryID);
		category.addProduct(product);

		categoryRepository.save(category);
		return product.getId();
	}
}
