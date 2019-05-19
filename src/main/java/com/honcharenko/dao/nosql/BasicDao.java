package com.honcharenko.dao.nosql;

import com.honcharenko.dao.NoSqlDao;
import com.honcharenko.entity.EntityId;
import com.honcharenko.entity.Property;
import com.honcharenko.observer.impl.DaoPublisher;
import com.honcharenko.util.ConnectionManager;
import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicDao<E extends EntityId> implements NoSqlDao<E> {

    private static final String ID_PROPERTY_NAME = "_id";

    @Override
    public List<E> getAll() throws SQLException {
        FindIterable<Document> entities = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName()).find();
        List<E> entes = new ArrayList<>();
        for (Document entity : entities) {
            entes.add(prepareDocumentsToentityList(entity));
        }
        return entes;
    }

    @Override
    public List<E> getByProperty(List<Property> properties) throws SQLException {
        BasicDBObject basicDBObject = new BasicDBObject();
        for (Property property : properties) {
            basicDBObject.put(property.getName(), property.getValue());
        }

        FindIterable<Document> entities = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName()).find(basicDBObject);
        List<E> ents = new ArrayList<>();
        for (Document entity : entities) {
            ents.add(prepareDocumentsToentityList(entity));
        }
        return ents;
    }

    @Override
    public E add(E e) throws SQLException {
        Document document = transofmrEntityToDocument(e);
        ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName())
                .insertOne(document);
        e.setId(document.get(ID_PROPERTY_NAME).toString());
        return e;
    }

    @Override
    public E  removeById(String id) throws SQLException {
        ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName())
                .deleteOne(new BasicDBObject(ID_PROPERTY_NAME, new ObjectId(id)));
        return null;
    }

    @Override
    public E update(E e) throws SQLException {
        if (e.getId() == null) {
            System.out.println("No id for update");
        }
        UpdateResult id = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName())
                .updateOne(new BasicDBObject(ID_PROPERTY_NAME, new ObjectId(String.valueOf(e.getId()))), prepareEntityToUpdate(e));
        return getById(id.toString());
    }

    @Override
    public E getById(String id) throws SQLException {
        BasicDBObject filter = new BasicDBObject();
        filter.put(ID_PROPERTY_NAME, new ObjectId(String.valueOf(id)));
        Document documentEntity = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName()).find(filter).first();
        return prepareDocumentsToentityList(documentEntity);
    }

    @Override
    public DaoPublisher getDaoPublish() {
        return null;
    }

    public abstract String getCollectionName();

    abstract E prepareDocumentsToentityList(Document entites);

    abstract Document transofmrEntityToDocument(E e);

    abstract BasicDBObject prepareEntityToUpdate(E e);
}
