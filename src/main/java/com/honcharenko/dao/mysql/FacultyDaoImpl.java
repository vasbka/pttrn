package com.honcharenko.dao.mysql;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Property;

import java.sql.SQLException;
import java.util.List;

public class FacultyDaoImpl implements DAO<Faculty> {
    public List<Faculty> getAll() {
        return null;
    }

    @Override
    public List<Faculty> getByProperty(List<Property> properties) throws SQLException {
        return null;
    }

    public Faculty getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Faculty add(Faculty faculty) {
        return null;
    }

    public boolean remove(Faculty faculty) {
        return false;
    }

    public Faculty removeById(int id) {
        return null;
    }

    public Faculty update(Faculty faculty) {
        return null;
    }

    @Override
    public Faculty getById(int id) {
        return null;
    }
}
