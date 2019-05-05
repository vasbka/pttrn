package com.honcharenko.service.impl;

import com.honcharenko.dao.DAO;
import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.service.Service;
import com.honcharenko.util.DaoManager;

import java.sql.SQLException;
import java.util.List;

public class BasicServicce<E> implements Service<E> {
    protected DaoManager daoManager;
    protected AbstractDaoFactory factory;

    @Override
    public E add(E inputObject) {
        DAO<E> enrolleeDao = factory.getEnrolleeDao();
        E enrollee = null;
        try {
            enrollee = enrolleeDao.add(inputObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollee;
    }

    @Override
    public E getById(int id) {
        return null;
    }

    @Override
    public E removeById(int id) {
        return null;
    }

    @Override
    public List<E> getAll() {
        return null;
    }

    @Override
    public E getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    @Override
    public E update(E e) {
        return null;
    }
}
