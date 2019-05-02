package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrolleeDaoImpl implements DAO<Enrollee> {

    Connection connection;

    public EnrolleeDaoImpl(Connection connection) {
        this.connection = connection;
    }

    public List<Enrollee> getAll() {
        Map<Integer, Enrollee> enrollees = new HashMap<>();
        try {
            ResultSet resultSet = connection.prepareStatement(Queries.SELECT_ENROLLEE_WITH_POINTS).executeQuery();
            while(resultSet.next()) {
                int id = resultSet.getInt(Fields.ENROLLEE_ID);
                Enrollee enrolleeToAdd;
                if (enrollees.containsKey(id)) {
                    enrolleeToAdd = enrollees.get(id);
                    enrolleeToAdd.addPoints(new PointBuilder()
                            .setId(resultSet.getInt(Fields.POINT_ID))
                            .setSubject(new SubjectBuilder().setId(resultSet.getInt(Fields.POINT_SUBJECT_ID)).build())
                            .setPoint(resultSet.getDouble(Fields.POINT_POINT))
                            .build());
                } else {
                    enrolleeToAdd = new EnrolleeBuilder()
                            .setId(id)
                            .setFirstName(resultSet.getString(Fields.ENROLLEE_FIRST_NAME))
                            .setLastName(resultSet.getString(Fields.ENROLLEE_LAST_NAME))
                            .setEmail(resultSet.getString(Fields.ENROLLEE_EMAIL))
                            .setLogin(resultSet.getString(Fields.ENROLLEE_LOGIN))
                            .setPassword(resultSet.getString(Fields.ENROLLEE_PASSWORD))
                            .build();
                }
                enrollees.put(enrolleeToAdd.getId(), enrolleeToAdd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(enrollees.values());
    }

    public Enrollee getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Enrollee add(Enrollee enrollee) {
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_ENROLLEE);
        int counter = 1;
        preparedStatement.setString(counter++, enrollee.getFirstName());
        preparedStatement.setString(counter++, enrollee.getLastName());
        preparedStatement.setString(counter++, enrollee.getLogin());
        preparedStatement.setString(counter++, enrollee.getPassword());
        preparedStatement.setString(counter++, enrollee.getEmail());
        preparedStatement.executeUpdate();
        preparedStatement.getGeneratedKeys();
        return null;
    }

    public boolean remove(Enrollee enrollee) {
        return false;
    }

    public Enrollee removeById(int id) {
        return null;
    }

    public Enrollee update(Enrollee enrollee) {
        return null;
    }

    public Enrollee getById(int id) {
        connection.

        return null;
    }

}
