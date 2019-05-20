package com.honcharenko.dao.nosql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.util.Fields;
import com.mongodb.BasicDBObject;
import org.bson.Document;

import java.sql.SQLException;

public class EnrolleeDaoNoSqlImpl extends BasicDao<Enrollee> {
    @Override
    public String getCollectionName() {
        return "enrollee";
    }

    @Override
    Enrollee prepareDocumentToEntity(Document entity) {
        return new EnrolleeBuilder()
                .setId(entity.get("_id").toString())
                .setFirstName(entity.get(Fields.ENROLLEE_FIRST_NAME).toString())
                .setLastName(entity.get(Fields.ENROLLEE_LAST_NAME).toString())
                .setLogin(entity.get(Fields.ENROLLEE_LOGIN).toString())
                .setPassword(entity.get(Fields.ENROLLEE_PASSWORD).toString())
                .setEmail(entity.get(Fields.ENROLLEE_EMAIL).toString())
                .build();
    }

    @Override
    Document transofmrEntityToDocument(Enrollee enrollee) {
        Document document = new Document();
        document.put(Fields.ENROLLEE_EMAIL, enrollee.getEmail());
        document.put(Fields.ENROLLEE_FIRST_NAME, enrollee.getFirstName());
        document.put(Fields.ENROLLEE_LAST_NAME, enrollee.getLastName());
        document.put(Fields.ENROLLEE_LOGIN, enrollee.getLogin());
        document.put(Fields.ENROLLEE_PASSWORD, enrollee.getPassword());
        return document;
    }

    @Override
    BasicDBObject prepareEntityToUpdate(Enrollee enrollee) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put(Fields.ENROLLEE_FIRST_NAME, enrollee.getFirstName());
        basicDBObject.put(Fields.ENROLLEE_LAST_NAME, enrollee.getLastName());
        basicDBObject.put(Fields.ENROLLEE_PASSWORD, enrollee.getPassword());
        basicDBObject.put(Fields.ENROLLEE_EMAIL, enrollee.getEmail());
        basicDBObject.put(Fields.ENROLLEE_LOGIN, enrollee.getLogin());
        return basicDBObject;
    }

}
