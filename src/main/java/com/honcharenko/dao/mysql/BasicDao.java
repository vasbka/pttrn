package com.honcharenko.dao.mysql;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.EntityId;
import com.honcharenko.entity.Property;
import com.honcharenko.observer.impl.DaoPublisher;
import com.honcharenko.util.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class BasicDao<E extends EntityId> implements DAO<E> {
    private String tableName;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private DaoPublisher daoPublisher;

    public BasicDao(String tableName) {
        this.tableName = tableName;
        this.daoPublisher = new DaoPublisher();
    }

    @Override
    public List<E> getAll() throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(QueryBuilder.ALL).from(tableName);
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build());
        resultSet = preparedStatement.executeQuery();
        List<E> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(extractEntityFromResultSet(resultSet));
        }
        close();
        return list;
    }

    @Override
    public List<E> getByProperty(List<Property> properties) throws SQLException {
        String conditions = properties.stream().map(property -> property.getName() + " = ?").collect(Collectors.joining(" AND "));
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(QueryBuilder.ALL).from(tableName).where(conditions);
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build());
        int count = 1;
        for (Property property : properties) {
            preparedStatement.setString(count++, property.getValue());
        }
        resultSet = preparedStatement.executeQuery();
        List<E> list = new ArrayList<>();
        while(resultSet.next()) {
            list.add(extractEntityFromResultSet(resultSet));
        }
        return list;
    }

    @Override
    public E add(E object) throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.insert(tableName).values(prepareEntityValuesToInsert(object));
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build(), Statement.RETURN_GENERATED_KEYS);
        preparedPropertiesValue(preparedStatement, object);
        preparedStatement.execute();
        resultSet = preparedStatement.getGeneratedKeys();
        if(resultSet.next()) {
            object.setId((int)resultSet.getLong(1));
        }
        close();
        daoPublisher.notifySubscribers("Entity " + object + " successfully created");
        return object;
    }

    @Override
    public E removeById(int id) throws SQLException {
        E e = getById(id);
        if (e == null){
            return null;
        }
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.delete().from(tableName).where(getIdColumnName() + "=?");
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build());
        preparedStatement.setInt(1, e.getId());
        preparedStatement.execute();
        close();
        daoPublisher.notifySubscribers("Entity " + e + " successfully removed.");
        return e;
    }

    @Override
    public E update(E e) throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.update(tableName).updateSetValues(getSetUpdateValues()).where(getIdColumnName() + " = ?");
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build());
        int lastInsertedIndex = preparedPropertiesValue(preparedStatement, e);
        preparedStatement.setInt(lastInsertedIndex, e.getId());
        preparedStatement.execute();
        return getById(e.getId());
    }

    @Override
    public E getById(int id) throws SQLException {
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(QueryBuilder.ALL).from(tableName).where(getIdColumnName() + " = ?");
        connection = ConnectionManager.getInstance().getConnection();
        preparedStatement = connection.prepareStatement(queryBuilder.build());
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        E object = null;
        if (resultSet.next()) {
            object = extractEntityFromResultSet(resultSet);
        }
        close();
        daoPublisher.notifySubscribers("Entity " + object + " successfully updated.");
        return object;
    }

    abstract E extractEntityFromResultSet(ResultSet resultSet) throws SQLException;

    abstract String prepareEntityValuesToInsert(E e);

    abstract int preparedPropertiesValue(PreparedStatement preparedStatement, E e) throws SQLException;

    abstract String getIdColumnName();

    abstract String getSetUpdateValues();

    private void close() throws SQLException {
        this.connection.close();
        this.preparedStatement.close();
        this.resultSet.close();
    }

    @Override
    public DaoPublisher getDaoPublish() {
        return this.daoPublisher;
    }
}
