package com.honcharenko.factory.impl;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.FacultyDaoImpl;
import com.honcharenko.factory.DaoFactoryMethod;

public class FacultyDaoFactoryImpl implements DaoFactoryMethod {
    public DAO getDao() {
        return new FacultyDaoImpl();
    }
}
