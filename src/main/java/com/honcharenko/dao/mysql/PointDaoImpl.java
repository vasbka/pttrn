package com.honcharenko.dao.mysql;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Property;

import java.sql.SQLException;
import java.util.List;

public class PointDaoImpl implements DAO<Point> {
    public List<Point> getAll() {
        return null;
    }

    @Override
    public List<Point> getByProperty(List<Property> properties) throws SQLException {
        return null;
    }

    public Point getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Point add(Point point) {
        return null;
    }

    public boolean remove(Point point) {
        return false;
    }

    public Point removeById(int id) {
        return null;
    }

    public Point update(Point point) {
        return null;
    }

    @Override
    public Point getById(int id) {
        return null;
    }
}
