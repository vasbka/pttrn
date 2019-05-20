package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.*;
import com.honcharenko.entity.*;

import java.util.HashMap;
import java.util.Map;

public class MySqlFactory implements AbstractDaoFactory {
    private static Map<Class, DAO> entityDao = new HashMap<>();
    private static final FacultyDaoImpl facultyDao = new FacultyDaoImpl();
    private static final EnrolleeDaoImpl enrolleeDao = new EnrolleeDaoImpl();
    private static final PointDaoImpl pointDao = new PointDaoImpl();
    private static final SubjectDaoImpl subjectDao = new SubjectDaoImpl();
    private static final SubjectTypeDaoImpl subjectTypeDaoImpl = new SubjectTypeDaoImpl();

    public MySqlFactory() {
        entityDao.put(Enrollee.class, enrolleeDao);
        entityDao.put(Faculty.class, facultyDao);
        entityDao.put(Point.class, pointDao);
        entityDao.put(Subject.class, subjectDao);
        entityDao.put(SubjectType.class, subjectTypeDaoImpl);
    }

    @Override
    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }

}
