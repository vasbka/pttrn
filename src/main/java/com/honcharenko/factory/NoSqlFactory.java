package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.FacultyDaoImpl;
import com.honcharenko.dao.nosql.EnrolleeDaoNoSqlImpl;
import com.honcharenko.dao.nosql.PointDaoNoSqlImpl;
import com.honcharenko.dao.nosql.SubjectDaoNoSqlImpl;
import com.honcharenko.dao.nosql.SubjectPopularityDaoNoSqlImpl;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;
import com.honcharenko.entity.SubjectPopularity;

import java.util.HashMap;
import java.util.Map;

public class NoSqlFactory implements AbstractDaoFactory {
    private static Map<Class, DAO> entityDao = new HashMap<>();
    private static final FacultyDaoImpl FACULTY_DAO = new FacultyDaoImpl();
    private static final EnrolleeDaoNoSqlImpl ENROLLEE_DAO_NO_SQL = new EnrolleeDaoNoSqlImpl();
    private static final SubjectDaoNoSqlImpl SUBJECT_DAO_NO_SQL = new SubjectDaoNoSqlImpl();
    private static final PointDaoNoSqlImpl POINT_DAO_NO_SQL = new PointDaoNoSqlImpl();
    private static final SubjectPopularityDaoNoSqlImpl SUBJECT_POPULARITY_DAO_NO_SQL
            = new SubjectPopularityDaoNoSqlImpl();

    public NoSqlFactory() {
        entityDao.put(Enrollee.class, ENROLLEE_DAO_NO_SQL);
        entityDao.put(Subject.class, SUBJECT_DAO_NO_SQL);
        entityDao.put(Point.class, POINT_DAO_NO_SQL);
        entityDao.put(SubjectPopularity.class, SUBJECT_POPULARITY_DAO_NO_SQL);
    }

    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }
}
