package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.util.ConnectionManager;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EnrolleeDaoImpl implements DAO<Enrollee> {

    public EnrolleeDaoImpl() {
    }

    public List<Enrollee> getAll() throws SQLException {
        Map<Integer, Enrollee> enrollees = new HashMap<>();
        Connection connection = ConnectionManager.getConnection();
        ResultSet resultSet = connection.prepareStatement(Queries.SELECT_ENROLLEE_WITH_POINTS).executeQuery();
        while(resultSet.next()) {
            int id = resultSet.getInt(Fields.ENROLLEE_ID);
            Enrollee enrolleeToAdd;
            if (enrollees.containsKey(id)) {
                enrolleeToAdd = enrollees.get(id);
                enrolleeToAdd.addPoints(buildPoint(resultSet));
            } else {
                enrolleeToAdd = buildEnrollee(resultSet);
            }
            enrollees.put(enrolleeToAdd.getId(), enrolleeToAdd);
        }
        connection.close();
        return new ArrayList<>(enrollees.values());
    }

    private Enrollee buildEnrollee(ResultSet resultSet) throws SQLException {
        return new EnrolleeBuilder()
                .setId(resultSet.getInt(Fields.ENROLLEE_ID))
                .setFirstName(resultSet.getString(Fields.ENROLLEE_FIRST_NAME))
                .setLastName(resultSet.getString(Fields.ENROLLEE_LAST_NAME))
                .setEmail(resultSet.getString(Fields.ENROLLEE_EMAIL))
                .setLogin(resultSet.getString(Fields.ENROLLEE_LOGIN))
                .setPassword(resultSet.getString(Fields.ENROLLEE_PASSWORD))
                .build();
    }

    private Point buildPoint(ResultSet resultSet) throws SQLException {
        return new PointBuilder()
                .setId(resultSet.getInt(Fields.POINT_ID))
                .setSubject(new SubjectBuilder().setId(resultSet.getInt(Fields.POINT_SUBJECT_ID)).build())
                .setPoint(resultSet.getDouble(Fields.POINT_POINT))
                .build();
    }

    public Enrollee getByProperty(String propertyName, String propertyValue) {
        return null;
    }

    public Enrollee add(Enrollee enrollee) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_ENROLLEE, Statement.RETURN_GENERATED_KEYS);
        int counter = 1;
        preparedStatement.setString(counter++, enrollee.getFirstName());
        preparedStatement.setString(counter++, enrollee.getLastName());
        preparedStatement.setString(counter++, enrollee.getLogin());
        preparedStatement.setString(counter++, enrollee.getPassword());
        preparedStatement.setString(counter, enrollee.getEmail());
        preparedStatement.executeUpdate();
        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            enrollee.setId((int) generatedKeys.getLong(1));
        }
        connection.close();
        return enrollee;
    }


    public Enrollee removeById(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        Enrollee enrollee = getById(id);
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_ENROLLEE_BY_ID);
        preparedStatement.setInt(0, id);
        preparedStatement.executeQuery();
        connection.close();
        return enrollee;
    }

    public Enrollee update(Enrollee enrollee) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.UPDATE_ENROLLEE_BY_ID);
        int count = 1;
        preparedStatement.setString(count++, enrollee.getFirstName());
        preparedStatement.setString(count++, enrollee.getLastName());
        preparedStatement.setString(count++, enrollee.getEmail());
        preparedStatement.setString(count++, enrollee.getLogin());
        preparedStatement.setString(count++, enrollee.getPassword());
        preparedStatement.setInt(count, enrollee.getId());
        return getById(enrollee.getId());
    }

    public Enrollee getById(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ENROLLEE_WITH_POINTS_BY_ID);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return buildEnrollee(resultSet);
        }
        connection.close();
        return null;
    }

}
