package com.honcharenko.service.impl;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.service.Service;
import com.honcharenko.util.DaoHandler;
import com.honcharenko.util.Entity;

import java.util.List;

public class EnrolleeService implements Service<Enrollee> {

    public EnrolleeService() {
    }

    @Override
    public boolean add(Enrollee enrollee) {
        return false;
    }

    @Override
    public boolean remove(Enrollee enrollee) {
        return false;
    }

    @Override
    public Enrollee getById(int id) {
        DaoHandler serviceHandler = DaoHandler.getServiceHandler();
        DAO<Enrollee> enrolleeDao = serviceHandler.getDaoByEntity(Entity.ENROLLEE);

        Enrollee byId = enrolleeDao.getById(id);
        return byId;
    }

    @Override
    public Enrollee removeById(int id) {
        return null;
    }

    @Override
    public List<Enrollee> getAll() {
        DaoHandler serviceHandler = DaoHandler.getServiceHandler();
        DAO enrolleeDao = serviceHandler.getDaoByEntity(Entity.ENROLLEE);

        return enrolleeDao.getAll();
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
