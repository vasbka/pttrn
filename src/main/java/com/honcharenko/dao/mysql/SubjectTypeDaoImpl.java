package com.honcharenko.dao.mysql;

import com.honcharenko.entity.SubjectType;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectTypeDaoImpl extends BasicDao<SubjectType> {
    private static final String TABLE_NAME = "subjectType";

    public SubjectTypeDaoImpl() {
        super(TABLE_NAME);
    }

    @Override
    SubjectType extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString(Fields.SUBJECT_TYPE_ID);
        String name = resultSet.getString(Fields.SUBJECT_TYPE_NAME);
        return new SubjectType(id, name);
    }

    @Override
    String prepareEntityValuesToInsert(SubjectType subjectType) {
        return Queries.SUBJECT_TYPE_INSERT;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, SubjectType subjectType) throws SQLException {
        int count = 1;
        preparedStatement.setString(count++, subjectType.getName());
        return count;
    }

    @Override
    String getIdColumnName() {
        return Fields.SUBJECT_TYPE_ID;
    }

    @Override
    String getSetUpdateValues() {
        return Queries.SUBJECT_TYPE_UPDATE;
    }

    @Override
    public List<SubjectType> getByAggregation(List<Document> aggregations) {
        return null;
    }
}
