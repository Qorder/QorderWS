package com.qorder.qorderws.model.menu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "MENUS")
public class Menu {
	
	@Id
	@GeneratedValue
	@Column(name = "MENU_ID")
	private Long id;
	
	@OneToMany(targetEntity = Category.class, fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name ="FK_MENU_ID")
	private List<Category> categoryList = new ArrayList<Category>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
}
