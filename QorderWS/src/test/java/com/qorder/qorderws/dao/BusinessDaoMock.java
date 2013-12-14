package com.qorder.qorderws.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class BusinessDaoMock implements IBusinessDAO {

	private List<Business> businessList = new ArrayList<Business>();

	public BusinessDaoMock() {
		createMockBussineses();
	}

	// http://snf-185147.vm.okeanos.grnet.gr:8080/qorderws/menus/business?id=;
	private void createMockBussineses() {
		Business business = new Business("To Meraki");
		business.setId(0);

		// category 1
		Category category1 = new Category();
		category1.setId(0);
		category1.setName("Food");
		
		List<Product> foodProductList = new ArrayList<Product>();

		// category 1 product 1
		Product product1 = new Product();
		product1.setId(0);
		product1.setName("Souvlaki");
		product1.setPrice(BigDecimal.valueOf(1.4));
		//product1.addAttribute("Chicken");
		//product1.addAttribute("pork");

		// category 1 product 2
		Product product2 = new Product();
		product2.setId(1);
		product2.setName("Giros");
		product2.setPrice(BigDecimal.valueOf(2.0));
		//product2.addAttribute("Chicken");
		//product2.addAttribute("pork");

		foodProductList.add(product1);
		foodProductList.add(product2);
		category1.setProductList(foodProductList);

		
		// category 2
		Category category2 = new Category();
		category2.setId(1);
		category2.setName("Drink");

		List<Product> drinkProductList = new ArrayList<Product>();

		// category 2 product 3
		Product product3 = new Product();
		product3.setId(2);
		product3.setName("Juice");
		product3.setPrice(BigDecimal.valueOf(2.0));
		//product3.addAttribute("Motion");
		//product3.addAttribute("3 fruits");
		//product3.addAttribute("7 vitamins");

		// category 2 product 4
		Product product4 = new Product();
		product4.setId(3);
		product4.setName("Tea");
		product4.setPrice(BigDecimal.valueOf(2.0));
		//product4.addAttribute("Sweet");
		//product4.addAttribute("semisweet");
		//product4.addAttribute("no sugar");

		drinkProductList.add(product3);
		drinkProductList.add(product4);
		category2.setProductList(drinkProductList);
		
		
		// category 3
		Category category3 = new Category();
		category3.setId(3);
		category3.setName("Coffee");

		List<Product> coffeeProductList = new ArrayList<Product>();

		// category 3 product 5
		Product product5 = new Product();
		product5.setId(4);
		product5.setName("Frappe");
		product5.setPrice(BigDecimal.valueOf(1.5));
		//product5.addAttribute("Sweet");
		//product5.addAttribute("Semisweet");
		//product5.addAttribute("no sugar");
		//product5.addAttribute("Milk");
		
		// category 3 product 6
		Product product6 = new Product();
		product6.setId(5);
		product6.setName("Cappuccino");
		product6.setPrice(BigDecimal.valueOf(2.5));
		//product6.addAttribute("Sweet");
		//product6.addAttribute("semisweet");
		//product6.addAttribute("no sugar");

		coffeeProductList.add(product5);
		coffeeProductList.add(product6);
		category3.setProductList(drinkProductList);

		
		//finally add all categories to business
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(category1);
		categoryList.add(category2);
		categoryList.add(category3);
		
		business.setCategoryList(categoryList);
		businessList.add(business);
	}

	@Override
	public boolean save(Business business) {
		return businessList.add(business);
	}

	@Override
	public boolean update(Business business)
			throws BusinessDoesNotExistException {
		if (business.getId() < businessList.size()) {
			businessList.set(0, business);
			return true;
		} else {
			throw new BusinessDoesNotExistException();
		}

	}

	@Override
	public Business findById(long businessId)
			throws BusinessDoesNotExistException {
		if (businessId < businessList.size()) {
			return businessList.get((int) businessId);
		} else {
			throw new BusinessDoesNotExistException();
		}
	}

	@Override
	public boolean delete(Business business) {
		// TODO Auto-generated method stub
		return false;
	}

}
