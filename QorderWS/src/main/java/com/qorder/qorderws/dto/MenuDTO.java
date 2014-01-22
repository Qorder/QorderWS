package com.qorder.qorderws.dto;

import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.dto.category.CategoryDTO;


public class MenuDTO {
	
	private Long id;
	
	private List<CategoryDTO> categoryInfoList = new ArrayList<CategoryDTO>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<CategoryDTO> getCategoryInfoList() {
		return categoryInfoList;
	}
	public void setCategoryInfoList(List<CategoryDTO> categoryInfoList) {
		this.categoryInfoList = categoryInfoList;
	}
	
	public void addCategoryInfo(CategoryDTO categoryInfo) {
		categoryInfoList.add(categoryInfo);
	}
}
