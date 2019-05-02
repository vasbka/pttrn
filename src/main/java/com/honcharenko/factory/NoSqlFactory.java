package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

public class NoSqlFactory implements AbstractDaoFactory {
    @Override
    public DAO<Enrollee> getEnrolleeDao() {
        return null;
    }

    @Override
    public DAO<Subject> getSubjectDao() {
        return null;
    }

    @Override
    public DAO<Faculty> getFacultyDao() {
        return null;
    }

    @Override
    public DAO<Point> getPointDao() {
        return null;
    }
}
