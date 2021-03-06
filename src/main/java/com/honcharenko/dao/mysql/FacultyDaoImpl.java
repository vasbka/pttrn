package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.FacultyBuilder;
import com.honcharenko.entity.Faculty;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FacultyDaoImpl extends BasicDao<Faculty> {
    private static final String TABLE_NAME = "faculty";

    public FacultyDaoImpl() {
        super(TABLE_NAME);
    }

    @Override
    Faculty extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new FacultyBuilder()
                .setId(resultSet.getString(Fields.FACULTY_ID))
                .setBudgetCount(resultSet.getInt(Fields.FACULTY_BUDGET_COUNT))
                .setFullName(resultSet.getString(Fields.FACULTY_NAME))
                .setTotalCount(resultSet.getInt(Fields.FACULTY_GENERAL_COUNT))
                .build();
    }

    @Override
    String prepareEntityValuesToInsert(Faculty faculty) {
        return Queries.FACULTY_INSERT;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Faculty faculty) throws SQLException {
        int count = 1;
        preparedStatement.setString(count++, faculty.getFullName());
        preparedStatement.setInt(count++, faculty.getTotalCount());
        preparedStatement.setInt(count++, faculty.getBudgetCount());
        return count;
    }

    @Override
    String getIdColumnName() {
        return Fields.FACULTY_ID;
    }

    @Override
    String getSetUpdateValues() {
        return Queries.FACULTY_UPDATE;
    }

    @Override
    public List<Faculty> getByAggregation(List<Document> aggregations) {
        return null;
    }
}
