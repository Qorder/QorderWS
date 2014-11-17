package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IProductDAO;
import com.qorder.qorderws.dto.product.DetailedProductDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.DetailedProductDTOtoProductMapper;
import com.qorder.qorderws.mapper.ProductToProductDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	private IProductDAO productDAO;

	
	@Transactional(readOnly = true)
	@Override
	public ProductDTO[] fetchCategoryByID(long categoryId) throws ResourceNotFoundException {
		Category fetchedCategory = categoryDAO.findById(categoryId);
		
		ProductToProductDTOMapper mapper = new ProductToProductDTOMapper();
		List<ProductDTO> productList = new ArrayList<ProductDTO>();
		fetchedCategory.getProductList().forEach((product) -> {
			productList.add(mapper.map(product, new ProductDTO()));
		} );
		return productList.toArray(new ProductDTO[productList.size()]);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long addProduct(long categoryID, DetailedProductDTO productDTO) throws ResourceNotFoundException {
		Product product = new DetailedProductDTOtoProductMapper().map(productDTO,new Product());
		
		Category category = categoryDAO.findById(categoryID);
		category.addProduct(product);
		
		productDAO.save(product);
		categoryDAO.update(category);
		
		return product.getId();
	}
	

	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public IProductDAO getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(IProductDAO productDAO) {
		this.productDAO = productDAO;
	}

}
