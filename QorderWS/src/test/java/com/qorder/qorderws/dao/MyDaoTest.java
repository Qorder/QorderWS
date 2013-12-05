package com.qorder.qorderws.dao;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.model.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class MyDaoTest {
	
	@Autowired
	@Qualifier("main")
	private IMenuDAO menuDAO;

	public IMenuDAO getMenuDao() {
		return menuDAO;
	}

	public void setMenuDao(IMenuDAO menuDao) {
		this.menuDAO = menuDao;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testCheck() {
		
		long businessId = 5;
		List<Category> categoryList = menuDAO.getCategoryListById(businessId);
		assertNotNull(categoryList);
	}

}
