package com.honcharenko.util;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionManager {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl("jdbc:mysql://localhost:1234/init");
        ds.setUsername("user");
        ds.setPassword("pwd");
        ds.setMinIdle(1);
        ds.setMaxActive(100);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
