package com.qorder.qorderws.dao;

import java.io.FileInputStream;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BusinessDaoTest extends DBTestCase {
	
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
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/com/qorder/qorderws/dao/DemoBusinesses.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed. 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.testBus = new Business();
	}

	@After
	public void tearDown() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.DELETE.execute(connection, getDataSet());
	}
	
	@Test
	public void testExistsFindById() throws BusinessDoesNotExistException {
		this.testBus = this.testBusinessDAO.findById(3);
		assertEquals("Jumbo3",this.testBus.getName());	
	}
	
	@Test(expected=BusinessDoesNotExistException.class)
	public void testDoesNotExistFindById() throws BusinessDoesNotExistException {
		this.testBus = this.testBusinessDAO.findById(1337);
	}
	
	@Test
	public void testExistsDelete() throws BusinessDoesNotExistException {
		this.testBus.setId(3);
		assertEquals(true, this.testBusinessDAO.delete(testBus));
	}
	

	@Test
	public void testExistsUpdate() throws BusinessDoesNotExistException {
		this.testBus.setId(2);
		this.testBus.setName("Trakter");
		assertEquals(true, this.testBusinessDAO.update(testBus));
	}
	
	
	@Test
	public void testNameDoesNotExistSave() throws BusinessDoesNotExistException {
		this.testBus = new Business("Jumbo5");
		assertEquals(true, this.testBusinessDAO.save(testBus));
	}

	@Test
	public void testNameExistsSave() throws BusinessDoesNotExistException {
		this.testBus = new Business("Jumbo4");
		boolean wasSaved = testBusinessDAO.save(testBus);
		assertEquals(true, wasSaved);
	}
}