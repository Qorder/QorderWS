package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.util.List;

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
	}
	
	//test an douleuei to EAGER
	@Test
	public void testGetCategoryList() throws BusinessDoesNotExistException {
		Business retBus = testBusinessDAO.findById(1);
		List<Category> testCatList = retBus.getCategoryList();
		//an h teleutaia eggrafh apo to dataset einai idia me t teleutaia ts listas, tote OK
		assertEquals(2,testCatList.get(1).getId());
	}
	
	//add new category
	@Test
	public void testSetCategoryListAddCategory() throws BusinessDoesNotExistException {
		//pairnw ta categories
		Business retBus = testBusinessDAO.findById(1);
		List<Category> testCatList = retBus.getCategoryList();
		//vazw new cat kai ta stelnw
		Category testCat = new Category();
		testCat.setName("newInjectedCategory");
		testCatList.add(testCat);
		retBus.setCategoryList(testCatList);
		testBusinessDAO.update(retBus);
		//ksana pernw ta categories apo vash k vlepw an mpike to testCat
		testCatList= retBus.getCategoryList();
		assertEquals(7, testCatList.get(2).getId());
	}

}
