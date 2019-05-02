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
import org.apache.commons.dbcp.BasicDataSource;

import java.sql.SQLException;

public class MySqlFactory implements AbstractDaoFactory {

    private BasicDataSource ds;

    public MySqlFactory(BasicDataSource dataSource) {
        this.ds = dataSource;
    }

    @Override
    public DAO<Enrollee> getEnrolleeDao() {
        try {
            return new EnrolleeDaoImpl(ds.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
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
