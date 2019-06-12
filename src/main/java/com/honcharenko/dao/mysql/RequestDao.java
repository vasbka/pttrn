package com.honcharenko.dao.mysql;

import com.honcharenko.entity.Request;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RequestDao extends BasicDao<Request> {
    private static final String TABLE_NAME = "request";
    public RequestDao() {
        super(TABLE_NAME);
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

    @Override
    public List<Request> getByAggregation(List<Document> aggregations) {
        return null;
    }
}
