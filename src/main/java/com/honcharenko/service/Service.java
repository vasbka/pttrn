package com.honcharenko.service;

import java.util.List;

public interface Service<E> {
    E add(E e);

    E getById(int id);

    E removeById(int id);

    List<E> getAll();

    E getByProperty(String propertyName, String propertyValue);

    E update(E e);

}
