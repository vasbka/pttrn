package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.*;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        String enrolleeId = point.getEnrollee().getId();
        if (enrolleeId == null) {
            List<Property> properties = new ArrayList<>();
            properties.add(new Property(Fields.ENROLLEE_EMAIL, point.getEnrollee().getEmail()));
            List<Enrollee> byProperty = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Enrollee.class).getByProperty(properties);
            enrolleeId = byProperty.get(0).getId();
        }

        String subjectId = point.getSubject().getId();
        if (subjectId == null) {
            List<Property> properties = new ArrayList<>();
            properties.add(new Property(Fields.SUBJECT_TYPE_NAME, point.getSubject().getSubjectType().getName()));
            List<SubjectType> byProperty = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(SubjectType.class).getByProperty(properties);
            point.getSubject().getSubjectType().setId(byProperty.get(0).getId());

            properties = new ArrayList<>();
            properties.add(new Property(Fields.SUBJECT_NAME, point.getSubject().getFullName()));
            properties.add(new Property(Fields.SUBJECT_SUBJECT_TYPE_ID, point.getSubject().getSubjectType().getId()));
            List<Subject> byProperty1 = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Subject.class).getByProperty(properties);
            subjectId = byProperty1.get(0).getId();
        }

        preparedStatement.setString(count++, enrolleeId);
        preparedStatement.setString(count++, subjectId);
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

    @Override
    public List<Point> getByAggregation(List<Document> aggregations) {
        return null;
    }
}
