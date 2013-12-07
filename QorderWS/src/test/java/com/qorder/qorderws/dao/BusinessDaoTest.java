package com.qorder.qorderws.dao;

import java.io.FileInputStream;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.jdbc.Connection;
import com.qorder.qorderws.exception.BusinessDoesNotExistException;
import com.qorder.qorderws.model.business.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/test-context.xml"})
public class BusinessDaoTest extends DBTestCase {
	
	@Autowired
	private IBusinessDAO businessDAO;
	@Autowired
	private DataSource dataSource;
	private Business testBus;

	public IBusinessDAO getBusinessDao() {
		return businessDAO;
	}

	public void setBusinessDao(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}
	
	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/java/com/qorder/qorderws/dao/DemoBusinesses.xml"));
	}
	
	/* Inserts XML dataset into the db before EACH test. if an item gets deleted, it will be reinserted
	 * before running the next test. Although, if an item is inserted manually, thus incrementing the id,
	 * the latter will be "consumed". 
	*/
	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
	}

	@After
	public void tearDown() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
		DatabaseOperation.DELETE.execute(connection, getDataSet());
	}
	
	@Test
	public void testExistsFindById() throws BusinessDoesNotExistException {
		Business retBus = businessDAO.findById(3);
		assertEquals("Jumbo3",retBus.getName());	
	}
	
	@Test
	public void testDoesNotExistFindById() throws BusinessDoesNotExistException {
		Business retBus = businessDAO.findById(1337);
		assertNull(retBus);
	}
	
	//1)Try to delete jumbo2(exists) - 2)Validate
	@Test
	public void testExistsDelete() throws BusinessDoesNotExistException {
		this.testBus = new Business("otiKaiNaValwThaDoulepsei-koitaeiMonoId");
		testBus.setId(2);
		boolean wasDeleted = businessDAO.delete(testBus);
		assertEquals(true, wasDeleted);
		Business retBus = businessDAO.findById(2);
		assertNull(retBus);	
	}
	
	//1)Try to update jumbo2(exists) - 2)Validate
	@Test
	public void testExistsUpdate() throws BusinessDoesNotExistException {
		this.testBus = new Business();
		testBus.setId(2);
		testBus.setName("Trakter");
		boolean wasUpdated = businessDAO.update(testBus);
		assertEquals(true, wasUpdated);
		Business retBus = businessDAO.findById(2);
		assertEquals("Trakter", retBus.getName());
	}
	
	//1)Try to save jumbo5(does not exist) - 2)Validate
	@Test
	public void testNameDoesNotExistSave() throws BusinessDoesNotExistException {
		this.testBus = new Business("Jumbo5");
		boolean wasSaved = businessDAO.save(testBus);
		assertEquals(true, wasSaved);
		Business retBus = businessDAO.findById(5); //known state so the id is 5
		assertEquals("Jumbo5", retBus.getName());
	}

	//1)Try to save jumbo4(exists) - 2)Validate
	@Test
	public void testNameExistsSave() throws BusinessDoesNotExistException {
		this.testBus = new Business("Jumbo4");
		boolean wasSaved = businessDAO.save(testBus);
		assertEquals(true, wasSaved);
		Business retBus = businessDAO.findById(6); //id should be 6 if new obj was saved
		assertEquals("Jumbo4", retBus.getName());
	}
}