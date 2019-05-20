package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;

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
        PointBuilder pointBuilder = new PointBuilder();
        DAO enrolleeDao = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Enrollee.class);
        DAO subjectDao = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Subject.class);
        Point build = pointBuilder
                .setEnrollee((Enrollee) enrolleeDao.getById(resultSet.getString(Fields.POINT_ENROLLEE_ID)))
                .setId(resultSet.getString(Fields.POINT_ID))
                .setPoint(resultSet.getDouble(Fields.POINT_POINT))
                .setSubject((Subject) subjectDao.getById(resultSet.getString(Fields.POINT_SUBJECT_ID)))
                .build();
        return build;
    }

    @Override
    String prepareEntityValuesToInsert(Point point) {
        return Queries.POINT_INSERT;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Point point) throws SQLException {
        int count = 1;
        preparedStatement.setString(count++, point.getEnrollee().getId());
        preparedStatement.setString(count++, point.getSubject().getId());
        preparedStatement.setDouble(count++, point.getPoint());
        return count;
    }

    @Override
    String getIdColumnName() {
        return Fields.POINT_ID;
    }

    @Override
    String getSetUpdateValues() {
        return Queries.POINT_UPDATE;
    }
}
