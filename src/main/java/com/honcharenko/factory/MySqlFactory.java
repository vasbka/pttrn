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
    private static final FacultyDaoImpl facultyDao = new FacultyDaoImpl();
    private static final EnrolleeDaoImpl enrolleeDao = new EnrolleeDaoImpl();
    private static final PointDaoImpl pointDao = new PointDaoImpl();
    private static final SubjectDaoImpl subjectDao = new SubjectDaoImpl();

    public MySqlFactory() {
        entityDao.put(Enrollee.class, enrolleeDao);
        entityDao.put(Faculty.class, facultyDao);
        entityDao.put(Point.class, pointDao);
        entityDao.put(Subject.class, subjectDao);
    }

    @Override
    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }

}
