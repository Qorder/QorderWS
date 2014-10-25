package com.qorder.qorderws.dao;

import java.io.FileInputStream;

import javax.sql.DataSource;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.qorder.qorderws.exception.PersistanceLayerException;
import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.business.Business;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BusinessDaoTest extends DBTestCase {

	@Autowired
	private IBusinessDAO businessDAO;
	
	@Autowired
	private DataSource dataSource;

	public IBusinessDAO getBusinessDAO() {
		return businessDAO;
	}

	public void setBusinessDAO(IBusinessDAO businessDAO) {
		this.businessDAO = businessDAO;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

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
	public void testExistsFindById() throws ResourceNotFoundException {
		Business someBusiness = businessDAO.findById(1);
		assertNotNull(someBusiness);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testBusinessNotFoundByID() throws ResourceNotFoundException {
		Business someBusiness = businessDAO.findById(1337);
		assertNull(someBusiness);
	}
	
	@Test
	public void testBusinessCreation() {
		System.out.println("Testing Business creation: ");
		Business newBusiness = new Business();
		try {
		Business persistedBusiness = businessDAO.save(newBusiness);
		Assert.assertNotNull(persistedBusiness.getMenu().getId());
		Assert.assertNotNull("Business Pesisted",persistedBusiness);
		}
		catch (PersistanceLayerException ex) {
			fail("Business has not persisted!");
		}
	}

}
