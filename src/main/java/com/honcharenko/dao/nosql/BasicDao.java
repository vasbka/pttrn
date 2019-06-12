package com.honcharenko.dao.nosql;

import com.honcharenko.dao.NoSqlDao;
import com.honcharenko.entity.EntityId;
import com.honcharenko.entity.Property;
import com.honcharenko.observer.impl.DaoPublisher;
import com.honcharenko.util.ConnectionManager;
import com.mongodb.BasicDBObject;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BasicDao<E extends EntityId> implements NoSqlDao<E> {

    private static final String ID_PROPERTY_NAME = "_id";

    @Override
    public void clearAll() throws SQLException {
        BasicDBObject document = new BasicDBObject();
        ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName())
                .deleteMany(document);

    }

    @Override
    public List<E> getAll() throws SQLException {
        FindIterable<Document> entities = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName()).find();
        List<E> entes = new ArrayList<>();
        for (Document entity : entities) {
            entes.add(prepareDocumentToEntity(entity));
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
            ents.add(prepareDocumentToEntity(entity));
        }
        return ents;
    }

    @Override
    public E add(E e) throws SQLException {
        Document document = transofmrEntityToDocument(e);
        MongoCollection<Document> collection = ConnectionManager.getInstance()
                .getNoSqlDataBase()
                .getCollection(getCollectionName());
        int count = 5_000;
        for (int i = 0; i < count; i++) {
            document.remove("_id");
            System.out.println(i);
            int a = 0;
            while(document.get("_id") == null && a < 3) {
                try {
                    collection.insertOne(document);
                    try{
                        Thread.sleep(1);
                    } catch (InterruptedException e1x) {}
                } catch (RuntimeException exc) {
                    try {
                        System.out.println("sleep");
                        Thread.sleep(3000);
                    } catch (InterruptedException ex) {
                    } finally {
                        a++;
                    }
                }
            }
        }
        e.setId(document.get(ID_PROPERTY_NAME).toString());
        System.out.println(count + " = " + collection.count());
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
        return prepareDocumentToEntity(documentEntity);
    }

    @Override
    public DaoPublisher getDaoPublish() {
        return null;
    }

    public abstract String getCollectionName();

    abstract E prepareDocumentToEntity(Document entites);

    abstract Document transofmrEntityToDocument(E e);

    abstract BasicDBObject prepareEntityToUpdate(E e);
}
