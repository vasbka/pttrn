package com.honcharenko.service;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Property;
import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import org.bson.Document;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BasicService<E> implements Service<E> {
    protected DaoManager daoManager;
    protected AbstractDaoFactory factory;
    protected Class<E> type;

    public BasicService(Class<E> type) {
        this.type = type;
    }

    @Override
    public E add(E inputObject) {
        DAO<E> dao = factory.getDaoByEntityType(type);
        E object = null;
        try {
            object = dao.add(inputObject);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public E getById(String id) {
        DAO<E> dao = factory.getDaoByEntityType(type);

        E byId = null;
        try {
            byId = dao.getById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byId;
    }

    @Override
    public E removeById(String id) {
        DAO<E> dao = factory.getDaoByEntityType(type);

        E byId = null;
        try {
            byId = dao.removeById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byId;
    }

    @Override
    public List<E> getAll() {
        DAO<E> dao = daoManager.getFactory().getDaoByEntityType(type);

        List<E> objects = new ArrayList<>();
        try {
            objects = dao.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public List<E> getByProperty(List<Property> properties) {
        DAO<E> dao = daoManager.getFactory().getDaoByEntityType(type);

        List<E> objects = new ArrayList<>();
        try {
            objects = dao.getByProperty(properties);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return objects;
    }

    @Override
    public E update(E input) {
        DAO<E> dao = daoManager.getFactory().getDaoByEntityType(type);
        E object = null;
        try {
            object = dao.update(input);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void migrate(DaoType daoTypeToMigrate) {
        List<E> all = this.getAll();
        DAO daoByEntityType = new DaoManager(daoTypeToMigrate).getFactory().getDaoByEntityType(type);
        all.forEach(e -> {
            try {
                daoByEntityType.add(e);
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
    }

    public List<E> getByAggregations(List<Document> aggregations) {
        AbstractDaoFactory factory = daoManager.getFactory();
        DAO<E> dao = factory.getDaoByEntityType(type);
        return dao.getByAggregation(aggregations);
    }

}
