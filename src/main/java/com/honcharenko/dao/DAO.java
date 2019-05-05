package com.honcharenko.dao;

import com.honcharenko.entity.Property;
import java.sql.SQLException;
import java.util.List;

public interface DAO<E> {
    List<E> getAll() throws SQLException;

    List<E> getByProperty(List<Property> properties) throws SQLException;

    E add(E e) throws SQLException;

    E removeById(int id) throws SQLException;

    E update(E e) throws SQLException;

    E getById(int id) throws SQLException;

    default void close(List<AutoCloseable> closeables) {
        closeables.forEach(autoCloseable -> {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
