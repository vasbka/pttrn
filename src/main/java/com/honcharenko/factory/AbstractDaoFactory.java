package com.honcharenko.factory;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

public interface AbstractDaoFactory {
    DAO<Enrollee> getEnrolleeDao();

    DAO<Subject> getSubjectDao();

    DAO<Faculty> getFacultyDao();

    DAO<Point> getPointDao();
}
