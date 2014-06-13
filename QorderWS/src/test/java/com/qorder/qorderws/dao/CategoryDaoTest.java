package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CategoryDaoTest extends DBTestCase {

	@Autowired
	private ICategoryDAO testCategoryDAO;
	
	@Autowired
	private DataSource testDataSource;

	public void setTestCategoryDAO(CategoryDAO testCategoryDAO) {
		this.testCategoryDAO = testCategoryDAO;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DemoDatabase.xml"));
	}

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}

	@Test
	public void testExistsFindById() throws ResourceNotFoundException, IOException {
		Category someCategory = testCategoryDAO.findById(1);
		assertNotNull(someCategory);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testCategoryNotFoundByID() throws ResourceNotFoundException, IOException {
		Category someCategory = testCategoryDAO.findById(1337);
		assertNull(someCategory);
	}

	@Test
	public void testSuccessfulUpdate() throws ResourceNotFoundException, IOException {
		Category someCategory = testCategoryDAO.findById(2);
		someCategory.setName("Updated category name");
		testCategoryDAO.update(someCategory);
		
		Category category = testCategoryDAO.findById(2);
		assertEquals(category.getName(), someCategory.getName());
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testSuccessfulDelete() throws ResourceNotFoundException, IOException {
		Category someCategory = testCategoryDAO.findById(3);
		testCategoryDAO.delete(someCategory);
		
		Category category = testCategoryDAO.findById(3);
		assertNull(category);
	}
	
	@Test
	public void testProductAccess() throws ResourceNotFoundException {
		Category someCategory = testCategoryDAO.findById(1);
		Product someProduct = someCategory.getProductList().get(0);
		
		assertNotNull(someProduct);
	}

}
