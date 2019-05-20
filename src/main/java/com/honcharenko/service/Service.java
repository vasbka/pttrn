package com.honcharenko.service;

import com.honcharenko.entity.Property;

import java.util.List;

public interface Service<E> {
    E add(E e);

    E getById(String id);

    E removeById(String id);

    List<E> getAll();

    List<E> getByProperty(List<Property> properties);

    E update(E e);

    void Migrate(List<E> list);

}
