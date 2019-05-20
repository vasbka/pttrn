package com.honcharenko.dao.nosql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.SubjectType;
import com.honcharenko.util.Fields;
import com.mongodb.BasicDBObject;
import org.bson.Document;

public class PointDaoNoSqlImpl extends BasicDao<Point> {
    @Override
    public String getCollectionName() {
        return "point";
    }

    @Override
    Point prepareDocumentToEntity(Document entites) {
        PointBuilder pointBuilder = new PointBuilder();
        EnrolleeBuilder enrolleeBuilder = new EnrolleeBuilder();
        SubjectBuilder subjectBuilder = new SubjectBuilder();
        Document enrollee1 = entites.get("enrollee", Document.class);
        if (enrollee1 != null) {
            enrolleeBuilder
                    .setFirstName(enrollee1.get(Fields.ENROLLEE_FIRST_NAME).toString())
                    .setLastName(enrollee1.get(Fields.ENROLLEE_LAST_NAME).toString())
                    .setLogin(enrollee1.get(Fields.ENROLLEE_LOGIN).toString())
                    .setPassword(enrollee1.get(Fields.ENROLLEE_PASSWORD).toString())
                    .setEmail(enrollee1.get(Fields.ENROLLEE_EMAIL).toString());
        }
        Document subjectDocumnet = entites.get("subject", Document.class);
        if (subjectDocumnet != null) {
            subjectBuilder.setFullName(subjectDocumnet.get(Fields.SUBJECT_NAME).toString())
                    .setSubjectType(new SubjectType("", subjectDocumnet.get(Fields.SUBJECT_TYPE_NAME).toString()));
        }
        pointBuilder.setEnrollee(enrolleeBuilder.build());
        pointBuilder.setPoint(Double.parseDouble(entites.get(Fields.POINT_POINT).toString()));
        pointBuilder.setId(entites.get("_id").toString());
        pointBuilder.setSubject(subjectBuilder.build());
        return pointBuilder.build();
    }

    @Override
    Document transofmrEntityToDocument(Point point) {
        Document document = new Document();
        document.put(Fields.POINT_POINT, point.getPoint());
        Document value = new Document();
        value.put(Fields.SUBJECT_NAME, point.getSubject().getFullName());
        value.put(Fields.SUBJECT_TYPE_NAME, point.getSubject().getSubjectType().getName());
        document.put("subject", value);
        Document enrolleeDoc = new Document();
        enrolleeDoc.put(Fields.ENROLLEE_FIRST_NAME, point.getEnrollee().getFirstName());
        enrolleeDoc.put(Fields.ENROLLEE_LAST_NAME, point.getEnrollee().getLastName());
        enrolleeDoc.put(Fields.ENROLLEE_EMAIL, point.getEnrollee().getEmail());
        enrolleeDoc.put(Fields.ENROLLEE_LOGIN, point.getEnrollee().getLogin());
        enrolleeDoc.put(Fields.ENROLLEE_PASSWORD, point.getEnrollee().getPassword());
        document.put("enrollee", enrolleeDoc);
        return document;
    }

    @Override
    BasicDBObject prepareEntityToUpdate(Point point) {
        BasicDBObject basicDBO = new BasicDBObject();
        basicDBO.put(Fields.POINT_POINT, point.getPoint());
        Document value = new Document();
        value.put(Fields.SUBJECT_NAME, point.getSubject().getFullName());
        value.put(Fields.SUBJECT_TYPE_NAME, point.getSubject().getSubjectType().getName());
        basicDBO.put("subject", value);
        Document enrolleeDoc = new Document();
        enrolleeDoc.put(Fields.ENROLLEE_FIRST_NAME, point.getEnrollee().getFirstName());
        enrolleeDoc.put(Fields.ENROLLEE_LAST_NAME, point.getEnrollee().getLastName());
        enrolleeDoc.put(Fields.ENROLLEE_EMAIL, point.getEnrollee().getEmail());
        enrolleeDoc.put(Fields.ENROLLEE_LOGIN, point.getEnrollee().getLogin());
        enrolleeDoc.put(Fields.ENROLLEE_PASSWORD, point.getEnrollee().getPassword());
        basicDBO.put("enrollee", enrolleeDoc);
        return null;
    }
}
