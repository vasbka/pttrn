package com.honcharenko.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static BasicDataSource ds = new BasicDataSource();
    private static ConnectionManager instance;

    private ConnectionManager() {
        ds.setUrl("jdbc:mysql://localhost:1234/init");
        ds.setUsername("user");
        ds.setPassword("pwd");
        ds.setMinIdle(1);
        ds.setMaxActive(100);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
