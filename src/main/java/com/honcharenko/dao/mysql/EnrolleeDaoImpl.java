package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.memento.EnrolleeCaretaker;
import com.honcharenko.util.ConnectionManager;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;
import org.bson.Document;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnrolleeDaoImpl extends BasicDao<Enrollee> {

    private static final String TABLE_NAME = "enrollee";
    public EnrolleeDaoImpl() {
        super(TABLE_NAME);
    }

    @Override
    public Enrollee update(Enrollee enrollee) throws SQLException {
        Enrollee update = super.update(enrollee);
        EnrolleeCaretaker.getInstance().addSnapshot(update.createSnapshot());
        return update;
    }

    @Override
    public List<Enrollee> getByAggregation(List<Document> aggregations) {
        return null;
    }

    @Override
    Enrollee extractEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return new EnrolleeBuilder()
                    .setId(resultSet.getString(Fields.ENROLLEE_ID))
                    .setFirstName(resultSet.getString(Fields.ENROLLEE_FIRST_NAME))
                    .setLastName(resultSet.getString(Fields.ENROLLEE_LAST_NAME))
                    .setEmail(resultSet.getString(Fields.ENROLLEE_EMAIL))
                    .setLogin(resultSet.getString(Fields.ENROLLEE_LOGIN))
                    .setPassword(resultSet.getString(Fields.ENROLLEE_PASSWORD))
                .build();
    }

    @Override
    String prepareEntityValuesToInsert(Enrollee enrollee) {
        return Queries.ENROLLEE_INSERT;
    }

    @Override
    int preparedPropertiesValue(PreparedStatement preparedStatement, Enrollee enrollee) throws SQLException {
        int count = 1;
        preparedStatement.setString(count++, enrollee.getFirstName());
        preparedStatement.setString(count++, enrollee.getLastName());
        preparedStatement.setString(count++, enrollee.getLogin());
        preparedStatement.setString(count++, enrollee.getPassword());
        preparedStatement.setString(count++, enrollee.getEmail());
        return count;
    }

    @Override
    String getIdColumnName() {
        return Fields.ENROLLEE_ID;
    }

    @Override
    String getSetUpdateValues() {
        return Queries.ENROLLEE_UPDATE;
    }


}
