package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.EnrolleeDaoImpl;
import com.honcharenko.dao.mysql.FacultyDaoImpl;
import com.honcharenko.dao.mysql.PointDaoImpl;
import com.honcharenko.dao.mysql.SubjectDaoImpl;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

public class MySqlFactory implements AbstractDaoFactory {

    @Override
    public DAO<Enrollee> getEnrolleeDao() {
        return new EnrolleeDaoImpl();
    }

    @Override
    public DAO<Subject> getSubjectDao() {
        return new SubjectDaoImpl();
    }

    @Override
    public DAO<Faculty> getFacultyDao() {
        return new FacultyDaoImpl();
    }

    @Override
    public DAO<Point> getPointDao() {
        return new PointDaoImpl();
    }
}
