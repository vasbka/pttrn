package com.honcharenko.dao.mysql;

import com.honcharenko.entity.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;s
import java.sql.SQLException;

public class RequestDao extends BasicDao<Request> {
    public RequestDao(String tableName) {
        super(tableName);
    }

    @Override
    Request extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    String prepareEntityValuesToInsert(Request request) {
        return null;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Request request) throws SQLException {
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
