package com.qorder.qorderws.controller;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.dto.CategoryDTO;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
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
		
		System.out.println("1: Check object characteristics after parsing from Json:");
		System.out.println("Business info: " + businessInfo.getBusinessName());
		Iterator<CategoryDTO> categoryItr = businessInfo.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(businessInfo);
	}
	
	@Test
	public final void testFailedGetMenuById() {
		System.out.println("\n\n2: Test menu controller exceptions for non stored objects:");
		long businessId = 2;
		BusinessInfoDTO fetchedInfo = null;
		try
		{
			 fetchedInfo =  client.requestForMenu("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId);
		}
		catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		assertNull(fetchedInfo);
	}
	
	@Test
	public final void testPutCategoryToBusiness() {
		System.out.println("\n\n3: Test successful category save to web service:");
		long businessId = 0;
		
		Category category = Mockito.mock(Category.class);
		Mockito.when(category.getName()).thenReturn("drinks");
		Mockito.when(category.getId()).thenReturn((long) 0);
		Mockito.when(category.getProductList()).thenReturn(new ArrayList<Product>());
		
		client.postNewCategory("http://localhost:8080/qorderws/categories/business?id=",businessId, category);
		
		BusinessInfoDTO businessInfo =  client.requestForMenu("http://localhost:8080/qorderws/businesses/menus/business?id=",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		System.out.println("Business info: " + businessInfo.getBusinessName());
		Iterator<CategoryDTO> categoryItr = businessInfo.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(businessInfo);
		
	}
}
