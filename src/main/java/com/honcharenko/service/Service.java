package com.honcharenko.service;

import com.honcharenko.entity.Property;

import java.util.List;

public interface Service<E> {
    E add(E e);

    E getById(int id);

    E removeById(int id);

    List<E> getAll();

    List<E> getByProperty(List<Property> properties);

    E update(E e);

}
