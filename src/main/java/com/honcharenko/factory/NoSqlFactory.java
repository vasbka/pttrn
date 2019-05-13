package com.honcharenko.factory;

import com.honcharenko.dao.DAO;

public class NoSqlFactory implements AbstractDaoFactory {
    @Override
    public DAO getDaoByEntityType(Class type) {
        return null;
    }
}
