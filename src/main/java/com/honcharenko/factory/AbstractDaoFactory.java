package com.honcharenko.factory;

import com.honcharenko.dao.DAO;

public interface AbstractDaoFactory {
    DAO getDaoByEntityType(Class type);
}
