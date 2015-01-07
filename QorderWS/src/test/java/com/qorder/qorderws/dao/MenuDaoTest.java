package com.qorder.qorderws.dao;

import java.io.FileInputStream;

import javax.sql.DataSource;

import com.qorder.qorderws.repository.IMenuDAO;
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

import com.qorder.qorderws.exception.ResourceNotFoundException;
import com.qorder.qorderws.model.menu.Menu;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MenuDaoTest extends DBTestCase {

	@Autowired
	private IMenuDAO testMenuDAO;
	
	@Autowired
	private DataSource testDataSource;
	
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
	public void testExistsFindById() {
		Menu menu = testMenuDAO.findById(4);
		Assert.assertNotNull(menu);
	}
	
	@Test(expected = ResourceNotFoundException.class)
	public void testMenuNotFoundByID() {
		Menu aMenu = testMenuDAO.findById(2500);
		Assert.assertNull(aMenu);
	}
}
