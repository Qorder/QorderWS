package com.qorder.qorderws.dao;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BusinessDaoTest {

	@Autowired
	private IBusinessDAO businessDAO;
	private Business testBus;

	public IBusinessDAO getBusinessDao() {
		return businessDAO;
	}

	public void setBusinessDao(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	@Before
	public void setUp() throws Exception {
		this.testBus = new Business("Jumbo");
		Category category1 = new Category();
		Category category2 = new Category();
		Category category3 = new Category();
		category1.setName("rofimata");
		category2.setName("afepsima");
		category3.setName("mpyres");
		this.testBus.getCategoryList().add(category1);
		this.testBus.getCategoryList().add(category2);
		this.testBus.getCategoryList().add(category3);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSuccessSaveBusiness() {
		boolean i = businessDAO.save(testBus);
		assertEquals(i,true);
		
	}
	
	@Test
	public void testSuccessUpdateBusiness(){
		Business business=this.businessDAO.findById(2);
		business.setName("mikel");
		boolean i = businessDAO.update(business);
		assertEquals(i,true);
		
	}
	
	@Test
	public void testSuccessDeleteBusiness(){
		testBus.setId(40);
		boolean i = businessDAO.delete(testBus);
		assertEquals(i,true);
	}
	
	@Test
	public void testSuccessfindBusinessById(){
		Business business=this.businessDAO.findById(2);
		assertNotNull(business);
	}

}
