package com.qorder.qorderws.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.model.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class CategoryDAOTest {

	@Autowired
	private ICategoryDAO categoryDAO;
	private Category category;
	private int businessId;
 	
	public ICategoryDAO getCategoryDao() {
		return categoryDAO;
	}

	public void setCategoryDao(ICategoryDAO categoryDAO) {
		this.categoryDAO = categoryDAO;
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
			
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		this.category = new Category();
		category.setName("rofimata");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoadCategoriesByBusinessId() {
		List<Category> categories = this.categoryDAO.loadCategoriesByBusinessId(businessId);
		assertNotNull(categories);
		
	}

	//@Test
	public long testsaveCategory(Category category){
		return 0;
		
	}
}
