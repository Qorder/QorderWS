package com.qorder.qorderws.dao;

import java.io.FileInputStream;
import java.io.IOException;
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
import com.qorder.qorderws.exception.CategoryDoesNotExistException;
import com.qorder.qorderws.model.business.Business;
import com.qorder.qorderws.model.category.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/test-context.xml" })
public class CategoryDaoTest extends DBTestCase {

	@Autowired
	private ICategoryDAO testCategoryDAO;
	@Autowired
	private IBusinessDAO testBusinessDAO;
	@Autowired
	private DataSource testDataSource;
	private Category testCat;
	private Business testBus;

	public void setTestCategoryDAO(CategoryDAO testCategoryDAO) {
		this.testCategoryDAO = testCategoryDAO;
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream(
				"src/test/resources/Dbunit/DbunitCategories.xml"));
	}

	@Before
	public void setUp() throws Exception {
		IDatabaseConnection connection = new DatabaseDataSourceConnection(
				testDataSource);
		DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
		this.testCat = new Category();
		this.testBus = new Business();
	}

	// TODO : Tests gia periptoseis pou den iparxei to category

	@Test
	public void testExistsFindById() throws CategoryDoesNotExistException,
			IOException {
		this.testCat = this.testCategoryDAO.findById(1);
		assertEquals("Jumbo1mpyres", this.testCat.getName());
	}

	@Test(expected = CategoryDoesNotExistException.class)
	public void testDoesNotExistFindById()
			throws CategoryDoesNotExistException, IOException {
		this.testCat = this.testCategoryDAO.findById(1337);
	}

	@Test
	public void testFetchCategoriesForBusiness()
			throws BusinessDoesNotExistException {
		List<Category> testCatsList = testCategoryDAO
				.fetchCategoriesForBusiness(1);
		assertEquals(6, testCatsList.size());
		assertEquals("Jumbo1Kafes6", testCatsList.get(5).getName());
	}

	@Test
	public void testExistsUpdate() throws CategoryDoesNotExistException,
			IOException {
		this.testCat.setId(1);
		this.testCat.setName("Jumbo1mpyresUPDATEDMEGALE");
		this.testCategoryDAO.update(testCat);
		// check an mpike
		this.testCat = this.testCategoryDAO.findById(1);
		assertTrue(this.testCat.getName().contentEquals(
				"Jumbo1mpyresUPDATEDMEGALE"));
	}

	@Test(expected = CategoryDoesNotExistException.class)
	public void testExistsDelete() throws CategoryDoesNotExistException,
			IOException {
		this.testCat.setId(8);
		this.testCategoryDAO.delete(testCat);
		this.testCategoryDAO.findById(8);
	}

	@Test(expected = CategoryDoesNotExistException.class)
	public void testDeleteOrphansAfterBusinessDeleted()
			throws CategoryDoesNotExistException, IOException,
			BusinessDoesNotExistException {
		this.testBus = (Business) this.testBusinessDAO.findById(4);
		this.testBusinessDAO.delete(this.testBus);
		this.testCategoryDAO.findById(9);
		this.testCategoryDAO.findById(10);
	}

}
