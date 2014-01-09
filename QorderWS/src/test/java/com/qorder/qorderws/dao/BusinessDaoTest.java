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
	private Business testBusiness;

	public IBusinessDAO getBusinessDao() {
		return testBusinessDAO;
	}

	public void setBusinessDao(IBusinessDAO businessDAO) {
		this.testBusinessDAO = businessDAO;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/DbunitBusinesses.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed. 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.testBusiness = new Business();
	}

	@After
	public void tearDown() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(testDataSource);
		DatabaseOperation.DELETE.execute(connection, getDataSet());
	}
	
	@Test
	public void testExistsFindById() throws BusinessDoesNotExistException {
		this.testBusiness =(Business) this.testBusinessDAO.findById(1);
		assertEquals("Jumbo1",this.testBusiness.getName());	
	}
	
	
	@Test(expected=BusinessDoesNotExistException.class)
	public void testDoesNotExistFindById() throws BusinessDoesNotExistException {
		this.testBusiness = (Business) this.testBusinessDAO.findById(1337);
	}
	
	@Test(expected=BusinessDoesNotExistException.class)
	public void testExistsDelete() throws BusinessDoesNotExistException {
		this.testBusiness.setId(3);
		this.testBusinessDAO.delete(testBusiness);
		this.testBusinessDAO.findById(3);
	}
	

	@Test
	public void testExistsUpdate() throws BusinessDoesNotExistException {
		this.testBusiness.setId(2);
		this.testBusiness.setName("Trakter");
		this.testBusinessDAO.update(testBusiness);
		this.testBusiness = (Business) this.testBusinessDAO.findById(2);
		assertTrue(this.testBusiness.getName().contentEquals("Trakter"));
	}
	
	
	@Test
	public void testNameDoesNotExistSave() throws BusinessDoesNotExistException {
		this.testBusiness = new Business();
		testBusiness.setName("Jumbo9");
		this.testBusinessDAO.save(testBusiness);
		this.testBusiness =  (Business) this.testBusinessDAO.findById(9);
		assertTrue(this.testBusiness.getName().contentEquals("Jumbo9"));
	}

}