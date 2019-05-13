package com.honcharenko.dao.mysql;

import com.honcharenko.entity.Property;
import com.honcharenko.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectDaoImpl extends BasicDao<Subject> {
    private static final String TABLE_NAME = "subject";

    public SubjectDaoImpl() {
        super(TABLE_NAME);
    }

    public List<Subject> getAll() {
        return null;
    }

    @Override
    public List<Subject> getByProperty(List<Property> properties) throws SQLException {
        return null;
    }

    public Subject getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Subject add(Subject subject) {
        return subject;
    }

    public boolean remove(Subject subject) {
        return false;
    }

    public Subject removeById(int id) {
        return null;
    }

    public Subject update(Subject subject) {
        return null;
    }

    @Override
    public Subject getById(int id) {
        return null;
    }

    @Override
    Subject extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    String prepareEntityValuesToInsert(Subject subject) {
        return null;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Subject subject) throws SQLException {
        return 0;
    }

    @Override
    String getIdColumnName() {
        return null;
    }

    @Override
    String getSetUpdateValues() {
        return null;
    }

}
