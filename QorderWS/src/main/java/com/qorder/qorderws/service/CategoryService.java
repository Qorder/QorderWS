package com.qorder.qorderws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.dao.ICategoryDAO;
import com.qorder.qorderws.dao.IMenuDAO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.product.ProductDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.CategoryDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.ProductToProductDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;

@Transactional
public class CategoryService implements ICategoryService {

	private ICategoryDAO categoryDAO;
	private IMenuDAO menuDAO;

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

	@Override
	public long createCategory(long menuId, CategoryDTO categoryDTO) throws ResourceNotFoundException {
		Category category = new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category());
		categoryDAO.save(category);
		
		Menu menu = menuDAO.findById(menuId);
		menu.getCategoryList().add(category);
		menuDAO.update(menu);
		
		return category.getId();
	}


	public ICategoryDAO getCategoryDAO() {
		return categoryDAO;
	}


	public void setCategoryDAO(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public IMenuDAO getMenuDAO() {
		return menuDAO;
	}

	public void setMenuDAO(IMenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
}
