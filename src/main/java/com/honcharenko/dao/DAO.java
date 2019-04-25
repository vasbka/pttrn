package com.honcharenko.dao;

import java.util.List;

public interface DAO<E> {
    List<E> getAll();

    E getByProperty(String propertyName, String propertyValue);

    boolean add(E e);

    boolean remove(E e);

    E removeById(int id);

    E update(E e);
}
