package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.model.category.Category;

public class Menu {

	private List<Category> categoryList = new ArrayList<Category>();

	public void addCategory(Category category) {
		this.categoryList.add(category);
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

}
