package com.qorder.qorderws.controller;

import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import com.qorder.qorderws.client.AppClient;
import com.qorder.qorderws.dto.BusinessInfoDTO;
import com.qorder.qorderws.dto.CategoryInfoDTO;

public class MenuControllerRespondTest {
	
	private AppClient client;
	
	@Before
	public void setUpBeforeClass() throws Exception {
		client = new AppClient();
	}

	@Test
	public final void testGetMenuById() {
		System.out.println("Test menu controller respond:");
		long businessId = 2;
		BusinessInfoDTO fetchedMenu =  client.requestForMenu("http://localhost:8080/qorderws/menus/business?id=5",businessId);
		System.out.println("Check object characteristics after parsing from Json:\n\n");
		Iterator<CategoryInfoDTO> categoryItr = fetchedMenu.getCategoryInfoList().iterator();
		while(categoryItr.hasNext())
		{
			CategoryInfoDTO categoryInfo = categoryItr.next();
			System.out.println(categoryInfo.toString());
		}
		assertNotNull(fetchedMenu);
	}

}
