package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.CategoryDTO;

public class Menu {

	private List<CategoryDTO> categoryList = new ArrayList<CategoryDTO>();

	public void addCategoryList(CategoryDTO category) {
		this.categoryList.add(category);
	}

	public List<CategoryDTO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryDTO> categoryList) {
		this.categoryList = categoryList;
	}

}
