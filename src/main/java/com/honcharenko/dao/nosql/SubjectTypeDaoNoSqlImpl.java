package com.honcharenko.dao.nosql;

import com.honcharenko.entity.SubjectType;
import com.honcharenko.util.Fields;
import com.mongodb.BasicDBObject;
import org.bson.Document;

public class SubjectTypeDaoNoSqlImpl extends BasicDao<SubjectType> {
    @Override
    public String getCollectionName() {
        return "subjectType";
    }

    @Override
    SubjectType prepareDocumentToEntity(Document entites) {
        return new SubjectType(
                entites.get("name").toString(),
                entites.get("id").toString());
    }

    @Override
    Document transofmrEntityToDocument(SubjectType subjectType) {
        Document document = new Document();
        document.put(Fields.SUBJECT_TYPE_NAME, subjectType.getName());
        return document;
    }

    @Override
    BasicDBObject prepareEntityToUpdate(SubjectType subjectType) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put(Fields.SUBJECT_TYPE_NAME, subjectType.getName());
        return basicDBObject;
    }
}
