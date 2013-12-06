package com.qorder.qorderws.dto;

import java.util.ArrayList;
import java.util.List;


public class BusinessInfoDTO {
	
	private String businessName;
	
	private List<CategoryDTO> categoryInfoList = new ArrayList<CategoryDTO>();
	
	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
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
