package com.honcharenko.dao;

import com.honcharenko.entity.Property;
import com.honcharenko.observer.impl.DaoPublisher;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    List<E> getAll() throws SQLException;

    List<E> getByProperty(List<Property> properties) throws SQLException;

    E add(E e) throws SQLException;

    E removeById(String id) throws SQLException;

    E update(E e) throws SQLException;

    E getById(String id) throws SQLException;

    DaoPublisher getDaoPublish();
}
