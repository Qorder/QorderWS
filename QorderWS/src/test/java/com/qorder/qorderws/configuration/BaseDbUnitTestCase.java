package com.qorder.qorderws.configuration;

import org.dbunit.DBTestCase;
import org.dbunit.database.DatabaseDataSourceConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;

/**
 * Abstract db unit test case, used as base class for test cases
 * that need to use transactions with a database instance.
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
    protected final IDataSet getDataSet() throws Exception {
        File dataSetFile = defaultDataSetFile();
        try(InputStream dataSetInputStream = new FileInputStream(dataSetFile)) {
            return new FlatXmlDataSetBuilder().build(dataSetInputStream);
        }
    }

    /**
     * Override this factory method to change the default dataset source of the test case.
     *
     * @return {@code File} the dataSet file.
     */
    @NotNull
    protected File defaultDataSetFile() {
        return new File("src/test/resources/dbunit/testdb.xml");
    }

    /**
     * Override this factory method to change the default database instance of the test case.
     *
     * @return <code>IDatabaseConnection</code> instance
     * @throws SQLException if connection can't properly bound dataSource.
     */
    @NotNull
    protected IDatabaseConnection defaultDatabaseConnection() throws SQLException {
        return new DatabaseDataSourceConnection(dataSource);
    }

    /**
	 * Inserts XML dataSet into the db before EACH test. if an item gets
	 * deleted, it will be reinserted before running the next test. Although, if
	 * an item is inserted manually, thus incrementing the id, the latter will
	 * be consumed.
	 */
    @Before
    public void setUp() throws Exception {
        IDatabaseConnection connection = defaultDatabaseConnection();
        try(DatabaseConnectionDecorator connectionAdapter = new DatabaseConnectionDecorator(connection)) {
            DatabaseOperation.CLEAN_INSERT.execute(connectionAdapter, getDataSet());
        }
    }

}
