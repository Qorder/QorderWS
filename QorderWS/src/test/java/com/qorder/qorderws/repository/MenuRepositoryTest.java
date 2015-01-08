package com.qorder.qorderws.repository;

import com.qorder.qorderws.WebServiceApplication;
import com.qorder.qorderws.model.menu.Menu;
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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebServiceApplication.class)
@ActiveProfiles("test")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MenuRepositoryTest extends DBTestCase {

	@Autowired
	private IMenuRepository menuRepository;
	
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
		boolean menuExists = menuRepository.exists(4L);
		assertTrue(menuExists);
	}
	
	@Test
	public void testMenuNotFoundByID() {
		Menu aMenu = menuRepository.findOne(2500L);
		Assert.assertNull(aMenu);
	}
}
