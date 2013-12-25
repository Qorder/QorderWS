package com.qorder.qorderws.model.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.qorder.qorderws.model.category.Category;

@Entity
@Table(name = "BUSINESS")
public class Business {

	@Id
	@GeneratedValue
	@Column(name = "BUSINESS_ID")
	private long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(targetEntity = Category.class, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name = "BUSINESS_ID")
	private List<Category> CategoryList = new ArrayList<Category>();

	public Business(String name) {
		this.name = name;
	}
	
	public Business() {
		this.name="";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Category> getCategoryList() {
		return CategoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		CategoryList = categoryList;
	}
}
