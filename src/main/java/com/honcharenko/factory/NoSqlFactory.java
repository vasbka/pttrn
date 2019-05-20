package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.FacultyDaoImpl;
import com.honcharenko.dao.nosql.EnrolleeDaoNoSqlImpl;
import com.honcharenko.dao.nosql.PointDaoNoSqlImpl;
import com.honcharenko.dao.nosql.SubjectDaoNoSqlImpl;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

import java.util.HashMap;
import java.util.Map;

public class NoSqlFactory implements AbstractDaoFactory {
    private static Map<Class, DAO> entityDao = new HashMap<>();
    private static final FacultyDaoImpl facultyDao = new FacultyDaoImpl();
    private static final EnrolleeDaoNoSqlImpl enrolleeDaoNosql = new EnrolleeDaoNoSqlImpl();
    private static final SubjectDaoNoSqlImpl subjectDaoNosql = new SubjectDaoNoSqlImpl();
    private static final PointDaoNoSqlImpl pointDaoNoSql = new PointDaoNoSqlImpl();

    public NoSqlFactory() {
        entityDao.put(Enrollee.class, enrolleeDaoNosql);
        entityDao.put(Subject.class, subjectDaoNosql);
        entityDao.put(Point.class, pointDaoNoSql);
    }

    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }
}
