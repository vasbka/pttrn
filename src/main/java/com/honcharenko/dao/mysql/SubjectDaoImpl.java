package com.honcharenko.dao.mysql;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Subject;

import java.util.List;

public class SubjectDaoImpl implements DAO<Subject> {
    public List<Subject> getAll() {
        return null;
    }

    public Subject getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Subject add(Subject subject) {
        return subject;
    }

    public boolean remove(Subject subject) {
        return false;
    }

    public Subject removeById(int id) {
        return null;
    }

    public Subject update(Subject subject) {
        return null;
    }

    @Override
    public Subject getById(int id) {
        return null;
    }
}
