package com.honcharenko.dao.mysql;

import com.honcharenko.entity.Point;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PointDaoImpl extends BasicDao<Point> {

    private static final String TABLE_NAME = "enrolleeSubject";

    public PointDaoImpl() {
        super(TABLE_NAME);
    }

    @Override
    Point extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return null;
    }

    @Override
    String prepareEntityValuesToInsert(Point point) {
        return null;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Point point) throws SQLException {
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
