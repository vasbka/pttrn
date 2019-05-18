package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.dao.mysql.EnrolleeDaoImpl;
import com.honcharenko.dao.mysql.FacultyDaoImpl;
import com.honcharenko.dao.nosql.EnrolleeDaoNosqlImpl;
import com.honcharenko.entity.Enrollee;

import java.util.HashMap;
import java.util.Map;

public class NoSqlFactory implements AbstractDaoFactory {
    private static Map<Class, DAO> entityDao = new HashMap<>();
    private static final FacultyDaoImpl facultyDao = new FacultyDaoImpl();
    private static final EnrolleeDaoNosqlImpl enrolleeDaoNosql = new EnrolleeDaoNosqlImpl();

    public NoSqlFactory() {
        entityDao.put(Enrollee.class, enrolleeDaoNosql);
    }

    public DAO getDaoByEntityType(Class type) {
        return entityDao.get(type);
    }
}
