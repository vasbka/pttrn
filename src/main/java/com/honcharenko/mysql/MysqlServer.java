package com.honcharenko.mysql;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static com.wix.mysql.EmbeddedMysql.anEmbeddedMysql;
import static com.wix.mysql.ScriptResolver.classPathScript;
import static com.wix.mysql.config.MysqldConfig.aMysqldConfig;
import static com.wix.mysql.distribution.Version.v5_7_latest;

public class MysqlServer {
    public static void main(String[] args) throws Exception{
        MysqldConfig config = aMysqldConfig(v5_7_latest)
                .withPort(1234)
                .withUser("user", "pwd")
                .build();


        EmbeddedMysql mysqld = anEmbeddedMysql(config)
                .addSchema("init", classPathScript("db/init.sql"))
                .start();

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:1234/init", "user", "pwd");
//        ResultSet resultSet = connection.prepareStatement("SELECT * FROM enrollee").executeQuery();
        DatabaseMetaData md = connection.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }
        Thread.sleep(10000);

        mysqld.stop();
    }
}
