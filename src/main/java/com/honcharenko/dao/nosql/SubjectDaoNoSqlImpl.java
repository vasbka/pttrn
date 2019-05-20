package com.honcharenko.dao.nosql;

import com.google.gson.Gson;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.dao.mysql.SubjectTypeDaoImpl;
import com.honcharenko.entity.Subject;
import com.honcharenko.entity.SubjectType;
import com.honcharenko.util.Fields;
import com.mongodb.BasicDBObject;
import org.bson.Document;

public class SubjectDaoNoSqlImpl extends BasicDao<Subject> {
    @Override
    public String getCollectionName() {
        return "subject";
    }

    @Override
    Subject prepareDocumentsToentityList(Document entites) {

        SubjectTypeDaoImpl subjectTypeDao = new SubjectTypeDaoImpl();
        Subject subject = new Subject();
        entites.forEach((key, value) -> {
            if (key.equals("_id")) {
                subject.setId(key);
            } else if (key.equals(Fields.SUBJECT_NAME)) {
                subject.setFullName(value.toString());
            } else if (key.equals(Fields.SUBJECT_TYPE_NAME)) {
                subject.setSubjectType(new SubjectType("0", value.toString()));
            }
        });
        return subject;
    }

    @Override
    Document transofmrEntityToDocument(Subject subject) {
        Document document = new Document();
        document.put("_id", subject.getId());
        document.put(Fields.SUBJECT_NAME, subject.getFullName());
        document.put(Fields.SUBJECT_TYPE_NAME, subject.getSubjectType().getName());
        return document;
    }

    @Override
    BasicDBObject prepareEntityToUpdate(Subject subject) {
        BasicDBObject basicDBObject = new BasicDBObject();
        basicDBObject.put(Fields.SUBJECT_NAME, subject.getFullName());
        basicDBObject.put(Fields.SUBJECT_TYPE_NAME, subject.getSubjectType().getName());
        return basicDBObject;
    }
}
