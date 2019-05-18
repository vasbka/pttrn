package com.honcharenko.dao;

import java.sql.SQLException;

public interface NoSqlDao<E> extends DAO<E>{
    String getCollectionName();

    E getById(String id) throws SQLException;
}
