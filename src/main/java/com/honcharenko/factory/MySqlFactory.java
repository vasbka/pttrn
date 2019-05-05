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

import java.util.HashMap;
import java.util.Map;

public class MySqlFactory implements AbstractDaoFactory {
    private static Map<Class, DAO> entityDao = new HashMap<>();

    public MySqlFactory() {
        entityDao.put(Enrollee.class, new EnrolleeDaoImpl());
        entityDao.put(Faculty.class, new FacultyDaoImpl());
        entityDao.put(Point.class, new PointDaoImpl());
        entityDao.put(Subject.class, new SubjectDaoImpl());
    }

    @Override
    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }

}
