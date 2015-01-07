package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.model.category.Category;
import com.qorder.qorderws.model.product.Product;
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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CategoryRepositoryTest extends DBTestCase {

	@Autowired
	private ICategoryRepository categoryRepository;
	
	@Autowired
	private DataSource dataSource;


	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DemoDatabase.xml"));
	}

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}

	@Test
	public void testExistsFindById() {
		boolean categoryExists = categoryRepository.exists(1L);
		assertTrue(categoryExists);
	}

	@Test
	public void testCategoryNotFoundByID() {
		Category someCategory = categoryRepository.findOne(1337L);
		assertNull(someCategory);
	}

	@Test
	public void testSuccessfulUpdate() {
		Category someCategory = categoryRepository.findOne(2L);
		someCategory.setName("Updated category name");
		categoryRepository.save(someCategory);
		
		Category category = categoryRepository.findOne(2L);
		assertEquals(category.getName(), someCategory.getName());
	}

	@Test
	public void testSuccessfulDelete() {
		Category someCategory = categoryRepository.findOne(3L);
		categoryRepository.delete(someCategory);
		
		Category category = categoryRepository.findOne(3L);
		assertNull(category);
	}
	
	@Test
	public void testProductAccess() {
		Category someCategory = categoryRepository.findOne(1L);
		Product someProduct = someCategory.getProductList().get(0);
		
		assertNotNull(someProduct);
	}

}
