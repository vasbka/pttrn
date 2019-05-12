package com.honcharenko.util;

import com.honcharenko.dao.DAO;
import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.factory.MySqlFactory;
import com.honcharenko.factory.NoSqlFactory;
import org.apache.commons.dbcp.BasicDataSource;

import java.util.HashMap;
import java.util.Map;

public class DaoHandler {

    public static final DaoType DEFAULT_DAO_TYPE = DaoType.MYSQL;
    private static DaoHandler instance;
    private DaoType daoType;
    private static Map<DaoType, AbstractDaoFactory> factories = new HashMap<>();


    private DaoHandler() {
        BasicDataSource dataSource = getDataSource();
        this.daoType = DEFAULT_DAO_TYPE;
        factories.put(DaoType.MYSQL, new MySqlFactory());
        factories.put(DaoType.NOSQL, new NoSqlFactory());
    }

    private BasicDataSource getDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl("jdbc:mysql://localhost:1234/init");
        ds.setUsername("user");
        ds.setPassword("pwd");
        ds.setMinIdle(2);
        ds.setMaxActive(4);
        ds.setMaxOpenPreparedStatements(20);
        return ds;
    }

    public static DaoHandler getServiceHandler() {
        if (instance == null) {
            instance = new DaoHandler();
        }
        return instance;
    }

    public void setDaoType(DaoType daoType) {
        this.daoType = daoType;
    }

    public DAO getDaoByEntity(Entity entity) {
        if (entity == Entity.ENROLLEE) {
            return factories.get(daoType).getEnrolleeDao();
        }
        else if (entity == Entity.FACULTY) {
            return factories.get(daoType).getFacultyDao();
        }
        else if (entity == Entity.SUBJECT) {
            return factories.get(daoType).getSubjectDao();
        }
        return factories.get(daoType).getPointDao();
    }
}
