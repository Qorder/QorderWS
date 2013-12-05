package com.qorder.qorderws.dto;

import java.util.ArrayList;
import java.util.List;


public class BusinessInfoDTO {
	
	private String businessName;
	
	private List<CategoryInfoDTO> categoryInfoList = new ArrayList<CategoryInfoDTO>();
	
	public String getBusinessName() {
		return businessName;
	}
	
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public List<CategoryInfoDTO> getCategoryInfoList() {
		return categoryInfoList;
	}
	public void setCategoryInfoList(List<CategoryInfoDTO> categoryInfoList) {
		this.categoryInfoList = categoryInfoList;
	}
	
	public void addCategoryInfo(CategoryInfoDTO categoryInfo) {
		categoryInfoList.add(categoryInfo);
	}
}
