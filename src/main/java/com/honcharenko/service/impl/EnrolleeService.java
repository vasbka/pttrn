package com.honcharenko.service.impl;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.service.Service;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeService implements Service<Enrollee> {

    private DaoManager daoManager;
    private AbstractDaoFactory factory;

    public EnrolleeService(DaoType daoType) {
        this.daoManager = new DaoManager(daoType);
        this.factory = daoManager.getFactory();
    }

    @Override
    public Enrollee add(Enrollee inputEnrollee) {
        DAO<Enrollee> enrolleeDao = factory.getEnrolleeDao();
        Enrollee enrollee = null;
        try {
            enrollee = enrolleeDao.add(inputEnrollee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollee;
    }

    @Override
    public Enrollee getById(int id) {
        DAO<Enrollee> enrolleeDao = factory.getEnrolleeDao();

        Enrollee byId = null;
        try {
            byId = enrolleeDao.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byId;
    }

    @Override
    public Enrollee removeById(int id) {
        return null;
    }

    @Override
    public List<Enrollee> getAll() {
        DAO<Enrollee> enrolleeDao = daoManager.getFactory().getEnrolleeDao();

        List<Enrollee> enrollees = new ArrayList<>();
        try {
            enrollees = enrolleeDao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enrollees;
    }

    @Override
    public Enrollee getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    @Override
    public Enrollee update(Enrollee enrollee) {
        return null;
    }
}
