package com.qorder.qorderws.dao;

import java.io.FileInputStream;

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

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class CategoryDaoTest extends DBTestCase {
	
	@Autowired
	private IBusinessDAO testBusinessDAO;
	@Autowired
	private DataSource testDataSource;
	private Business testBus;
	private Category testCat;

	public IBusinessDAO getBusinessDao() {
		return testBusinessDAO;
	}

	public void setBusinessDao(IBusinessDAO businessDAO) {
		this.testBusinessDAO = businessDAO;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/com/qorder/qorderws/dao/DemoCategories.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed. 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.testCat = new Category();
	}
	

	@Test
	public void testGetCategoryList() throws BusinessDoesNotExistException {
		this.testBus=testBusinessDAO.findById(1);
		assertNotNull(this.testBus.getCategoryList().get(1));
	}
	
	@Test
	public void testGetCategoryListThatIsNull() throws BusinessDoesNotExistException {
		this.testBus=testBusinessDAO.findById(2);
		assertEquals(0,this.testBus.getCategoryList().size());
	}
	
	@Test
	public void testAddCategory() throws BusinessDoesNotExistException {
		this.testCat.setName("newInjectedCategory");
		this.testBus = this.testBusinessDAO.findById(1);
		this.testBus.getCategoryList().add(testCat);
		testBusinessDAO.update(this.testBus);
		this.testBus = this.testBusinessDAO.findById(1);
		assertEquals(7, this.testBus.getCategoryList().size());
	}
	
	@Test
	public void testDeleteCategory() throws BusinessDoesNotExistException {
		this.testBus = this.testBusinessDAO.findById(1);
		this.testBus.getCategoryList().remove(5);
		this.testBusinessDAO.update(this.testBus);
		assertEquals(5, this.testBus.getCategoryList().size());
		
	}
	
	@Test
	public void testUpdateCategoryName() throws BusinessDoesNotExistException {
		this.testBus = this.testBusinessDAO.findById(1);
		this.testBus.getCategoryList().get(1).setName("banana");
		this.testBusinessDAO.update(this.testBus);
		this.testBus = this.testBusinessDAO.findById(1);
		assertEquals("banana", this.testBus.getCategoryList().get(1).getName());
	}
	
	@Test
	public void testDeleteAllCategories() throws BusinessDoesNotExistException {
		this.testBus = this.testBusinessDAO.findById(1);
		this.testBus.getCategoryList().clear();
		this.testBusinessDAO.update(this.testBus);
		assertEquals(0, this.testBus.getCategoryList().size());	
	}
	
	
	
}
