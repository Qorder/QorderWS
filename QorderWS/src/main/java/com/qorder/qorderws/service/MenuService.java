package com.qorder.qorderws.service;


import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.dto.MenuDTO;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.mapper.CategoryDTOtoCategoryMapper;
import com.qorder.qorderws.mapper.MenuToMenuDTOMapper;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.menu.Menu;
import com.qorder.qorderws.repository.IMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuService implements IMenuService {

	private final IMenuRepository menuRepository;

	@Autowired
	public MenuService(IMenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Transactional(readOnly = true)
	@Override
	public MenuDTO fetchMenuById(long menuId) throws ResourceNotFoundException {
		Menu menu = menuRepository.findOne(menuId);
		return new MenuToMenuDTOMapper().map(menu, new MenuDTO());
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	@Override
	public long addCategory(long menuID, CategoryDTO categoryDTO) {
		Category category = new CategoryDTOtoCategoryMapper().map(categoryDTO, new Category());
		
		Menu menu = menuRepository.findOne(menuID);
		menu.addCategory(category);
		menuRepository.save(menu);
		
		return category.getId();
	}
	
}
