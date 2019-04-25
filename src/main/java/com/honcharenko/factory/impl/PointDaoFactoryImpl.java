package com.honcharenko.factory.impl;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.impl.PointDaoImpl;
import com.honcharenko.factory.DaoFactoryMethod;

public class PointDaoFactoryImpl implements DaoFactoryMethod {
    public DAO getDao() {
        return new PointDaoImpl();
    }
}
