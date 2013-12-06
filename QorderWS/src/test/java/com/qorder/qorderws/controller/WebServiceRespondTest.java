package com.qorder.qorderws.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.dto.CategoryInfoDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

public class WebServiceRespondTest {
	
	private AppClient client;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		client = new AppClient();
	}

	@Test
	public final void testSuccessfulGetMenuById() {
		System.out.println("Test menu controller succesful respond:");
		long businessId=0;
		BusinessInfoDTO businessInfo =  client.requestForMenu("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		System.out.println("Business info: " + businessInfo.getBusinessName());
		Iterator<CategoryInfoDTO> categoryItr = businessInfo.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryInfoDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(businessInfo);
	}
	
	@Test
	public final void testFailedGetMenuById() {
		System.out.println("Test menu controller false respond:");
		long businessId = 2;
		BusinessInfoDTO fetchedMenu =  client.requestForMenu("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		Iterator<CategoryInfoDTO> categoryItr = fetchedMenu.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryInfoDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNull(fetchedMenu);
	}
	
	@Test
	public final void testPutCategoryToBusiness() {
		System.out.println("Test successful category put:");
		long businessId = 0;
		Category category = Mockito.mock(Category.class);
		Mockito.when(category.getName()).thenReturn("foods");
		Mockito.when(category.getId()).thenReturn((long) 0);
		Mockito.when(category.getProductList()).thenReturn(new ArrayList<Product>());
		client.postNewCategory("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId, category);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		Mockito.verify(category).getName();
	}
	
	@Test
	public final void testSuccessfulCategoryCreation() {
		System.out.println("Test succesful category creation:");
		long businessId=0;
		BusinessInfoDTO businessInfo =  client.requestForMenu("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		System.out.println("Business info: " + businessInfo.getBusinessName());
		Iterator<CategoryInfoDTO> categoryItr = businessInfo.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryInfoDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(businessInfo);
	}

}
