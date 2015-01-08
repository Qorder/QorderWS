package com.qorder.qorderws.repository;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.FileInputStream;

/**
 * Abstract db unit test case, used as base class for test cases
 * that need to integrate transactions to in memory database.
 *
 * @author Grigorios
 */
@ActiveProfiles("test")
@Transactional
@TransactionConfiguration(defaultRollback = true)
public abstract class BaseDbUnitTestCase extends DBTestCase {

    @Autowired
    private DataSource dataSource;


    @Override
    protected IDataSet getDataSet() throws Exception {
        return new FlatXmlDataSetBuilder().build(new FileInputStream("src/test/resources/Dbunit/testdb.xml"));
    }

    /**
	 * Inserts XML dataSet into the db before EACH test. if an item gets
	 * deleted, it will be reinserted before running the next test. Although, if
	 * an item is inserted manually, thus incrementing the id, the latter will
	 * be consumed.
	 */
    @Before
    public void setUp() throws Exception {
        IDatabaseConnection connection = new DatabaseDataSourceConnection(dataSource);
        DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
    }

    @After
    public void restoreDB() {
        //DatabaseOperation
    }

}
