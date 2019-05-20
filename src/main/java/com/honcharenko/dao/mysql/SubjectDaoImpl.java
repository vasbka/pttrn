package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Property;
import com.honcharenko.entity.Subject;
import com.honcharenko.entity.SubjectType;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SubjectDaoImpl extends BasicDao<Subject> {
    private static final String TABLE_NAME = "subject";

    public SubjectDaoImpl() {
        super(TABLE_NAME);
    }


    @Override
    Subject extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        SubjectBuilder subjectBuilder = new SubjectBuilder();
        DAO subjectDaoImpl = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(SubjectType.class);
        Object byId = subjectDaoImpl.getById(resultSet.getString(Fields.SUBJECT_SUBJECT_TYPE_ID));
        Subject build = subjectBuilder
                .setId(resultSet.getString(Fields.SUBJECT_ID))
                .setFullName(resultSet.getString(Fields.SUBJECT_NAME))
                .setSubjectType((SubjectType) byId)
                .build();
        return build;
    }

    @Override
    String prepareEntityValuesToInsert(Subject subject) {
        return Queries.SUBJECT_INSERT;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Subject subject) throws SQLException {
        int count = 1;
        preparedStatement.setString(count++, subject.getFullName());
        preparedStatement.setString(count++, subject.getSubjectType().getId());
        return count;
    }

    @Override
    String getIdColumnName() {
        return Fields.SUBJECT_ID;
    }

    @Override
    String getSetUpdateValues() {
        return Queries.SUBJECT_UPDATE;
    }
}
