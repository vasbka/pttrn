package com.honcharenko.util;

import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.factory.MySqlFactory;
import com.honcharenko.factory.NoSqlFactory;

import java.util.HashMap;
import java.util.Map;

public class DaoManager {
    private DaoType daoType;
    private static Map<DaoType, AbstractDaoFactory> factories = new HashMap<>();

    public DaoManager(DaoType daoType) {
        this.daoType = daoType;
        factories.put(DaoType.MYSQL, new MySqlFactory());
        factories.put(DaoType.NOSQL, new NoSqlFactory());
    }

    public AbstractDaoFactory getFactory() {
        return factories.get(daoType);
    }
}
