package com.honcharenko.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EnableAutoConfiguration(exclude={MongoAutoConfiguration.class})
public class ConnectionManager {
    private static BasicDataSource ds = new BasicDataSource();
    private static ConnectionManager instance;
    private final MongoClient mongoClient;
    private final MongoDatabase db;

    private ConnectionManager() {
        //nosql
        mongoClient = new MongoClient("localhost", 27020);
        db = mongoClient.getDatabase("test");

        //mysql
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

    public Connection getMySqlConnection() throws SQLException {
        return ds.getConnection();
    }

    public MongoDatabase getNoSqlDataBase() {
        return db;
    }

}
