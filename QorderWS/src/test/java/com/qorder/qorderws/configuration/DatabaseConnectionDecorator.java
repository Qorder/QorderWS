package com.qorder.qorderws.configuration;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.statement.IStatementFactory;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Decorates a provided dbunit's database connection instance, with <code>AutoCloseable</code>
 * {@code close} method. This allows the adapted connection instance to be used with <a href="http://bit.ly/199HjSs">try with resources</a>,
 * statement that was introduced in Java SE 7.
 *
 * @author Grigorios
 */
public class DatabaseConnectionDecorator implements IDatabaseConnection, AutoCloseable {

    private final IDatabaseConnection connection;

    public DatabaseConnectionDecorator(IDatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }


    @Override
    public Connection getConnection() throws SQLException {
        return connection.getConnection();
    }

    @Override
    public String getSchema() {
        return connection.getSchema();
    }

    @Override
    public IDataSet createDataSet() throws SQLException {
        return connection.createDataSet();
    }

    @Override
    public IDataSet createDataSet(String[] tableNames) throws SQLException, DataSetException {
        return connection.createDataSet(tableNames);
    }

    @Override
    public ITable createQueryTable(String tableName, String sql) throws DataSetException, SQLException {
        return connection.createQueryTable(tableName, sql);
    }

    @Override
    public ITable createTable(String tableName, PreparedStatement preparedStatement) throws DataSetException, SQLException {
        return connection.createTable(tableName, preparedStatement);
    }

    @Override
    public ITable createTable(String tableName) throws DataSetException, SQLException {
        return connection.createTable(tableName);
    }

    @Override
    public int getRowCount(String tableName) throws SQLException {
        return connection.getRowCount(tableName);
    }

    @Override
    public int getRowCount(String tableName, String whereClause) throws SQLException {
        return connection.getRowCount(tableName, whereClause);
    }

    @Override
    public DatabaseConfig getConfig() {
        return connection.getConfig();
    }

    @Deprecated
    @Override
    public IStatementFactory getStatementFactory() {
        return connection.getStatementFactory();
    }
}
