package com.honcharenko.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    List<E> getAll() throws SQLException;

    E getByProperty(String propertyName, String propertyValue);

    E add(E e) throws SQLException;

    E removeById(int id) throws SQLException;

    E update(E e) throws SQLException;

    E getById(int id) throws SQLException;

}
