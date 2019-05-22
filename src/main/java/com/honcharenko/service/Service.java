package com.honcharenko.service;

import com.honcharenko.entity.EntityId;
import com.honcharenko.entity.Property;
import com.honcharenko.util.DaoType;

import java.util.List;

public interface Service<E> {
    E add(E e);

    E getById(String id);

    E removeById(String id);

    List<E> getAll();

    List<E> getByProperty(List<Property> properties);

    E update(E e);

    void migrate(DaoType daoTypeToMigrate);

}
