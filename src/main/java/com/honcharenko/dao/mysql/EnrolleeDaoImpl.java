package com.honcharenko.dao.mysql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Property;
import com.honcharenko.util.ConnectionManager;
import com.honcharenko.util.Fields;
import com.honcharenko.util.Queries;
import de.flapdoodle.embed.process.collections.Collections;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EnrolleeDaoImpl implements DAO<Enrollee> {

    public EnrolleeDaoImpl() {
    }

    public List<Enrollee> getAll() throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        String select = "enrollee.*, enrolleeSubject.*";
        String query = queryBuilder
                .select(select).from("enrollee").leftJoin("enrolleeSubject").on("enrolleeSubjectEnrolleeId = enrollee.enrolleeId")
                .union()
                .select(select).from("enrollee").rightJoin("enrolleeSubject").on("enrolleeSubjectEnrolleeId = enrollee.enrolleeId").build();
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Integer, Enrollee> enrollees = extractEmployeesToList(resultSet);
        close(Collections.newArrayList(preparedStatement, connection));
        return new ArrayList<>(enrollees.values());
    }

    public List<Enrollee> getByProperty(List<Property> properties) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        QueryBuilder queryBuilder = new QueryBuilder();
        String select = "enrollee.*, enrolleeSubject.*";
        String res = properties.stream().map(property -> property.getName() + " = ?").collect(Collectors.joining(" AND "));
        String query = buildQueryGetByProperties(queryBuilder, select, res);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        int count = 1;
        for (int i = 0; i < 2; i++) {
            for (Property property : properties) {
                try {
                    preparedStatement.setString(count++, property.getValue());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<Integer, Enrollee> enrollees = extractEmployeesToList(resultSet);
        close(Collections.newArrayList(preparedStatement, connection));
        return new ArrayList<>(enrollees.values());
    }

    private Map<Integer, Enrollee> extractEmployeesToList(ResultSet resultSet) throws SQLException {
        Map<Integer, Enrollee> enrollees = new HashMap<>();
        while (resultSet.next()) {
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
        return enrollees;
    }

    private String buildQueryGetByProperties(QueryBuilder queryBuilder, String select, String res) {
        return queryBuilder
                .select(select).from("enrollee").leftJoin("enrolleeSubject").on("enrolleeSubjectEnrolleeId = enrollee.enrolleeId")
                .where(res)
                .union()
                .select(select).from("enrollee").rightJoin("enrolleeSubject").on("enrolleeSubjectEnrolleeId = enrollee.enrolleeId")
                .where(res)
                .build();
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
        close(Collections.newArrayList(preparedStatement, connection));
        return enrollee;
    }


    public Enrollee removeById(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        Enrollee enrollee = getById(id);
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.DELETE_ENROLLEE_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        close(Collections.newArrayList(preparedStatement, connection));
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
        close(Collections.newArrayList(preparedStatement, connection));
        return getById(enrollee.getId());
    }

    public Enrollee getById(int id) throws SQLException {
        Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Queries.SELECT_ENROLLEE_WITH_POINTS_BY_ID);
        preparedStatement.setInt(1, id);
        preparedStatement.setInt(2, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            return buildEnrollee(resultSet);
        }
        close(Collections.newArrayList(preparedStatement, connection));
        return null;
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
}
