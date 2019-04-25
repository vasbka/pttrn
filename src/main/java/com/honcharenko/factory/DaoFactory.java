package com.honcharenko.factory;

import com.honcharenko.factory.impl.EnrolleeDaoFactoryImpl;
import com.honcharenko.factory.impl.FacultyDaoFactoryImpl;
import com.honcharenko.factory.impl.PointDaoFactoryImpl;
import com.honcharenko.factory.impl.SubjectDaoFactoryImpl;
import com.honcharenko.util.Entity;

import java.util.HashMap;

public class DaoFactory {
    private static HashMap<Entity, DaoFactoryMethod> factories;
    private DaoFactory daoFactory;

    private DaoFactory() {
        factories.put(Entity.ENROLLEE, new EnrolleeDaoFactoryImpl());
        factories.put(Entity.FACULTY, new FacultyDaoFactoryImpl());
        factories.put(Entity.POINT, new PointDaoFactoryImpl());
        factories.put(Entity.SUBJECT, new SubjectDaoFactoryImpl());
    }

    public DaoFactory getInstance() {
        if (daoFactory == null) {
            daoFactory = new DaoFactory();
        }
        return daoFactory;
    }

    public DaoFactoryMethod getDaoFactoryMethodByEntity(Entity entity) {
        return factories.get(entity);
    }

}
