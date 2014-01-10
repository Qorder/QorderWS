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
	private long id;
	
	@OneToMany(targetEntity = Category.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
	@JoinColumn(name ="MENU_ID")
	private List<Category> CategoryList = new ArrayList<Category>();
	
	public List<Category> getCategoryList() {
		return CategoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		CategoryList = categoryList;
	}

}
