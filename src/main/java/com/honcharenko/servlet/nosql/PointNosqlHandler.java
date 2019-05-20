package com.honcharenko.servlet.nosql;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;
import com.honcharenko.entity.SubjectType;
import com.honcharenko.servlet.PointHandler;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class PointNosqlHandler extends PointHandler {

    public PointNosqlHandler(DaoType daoType) {
        super(daoType);
    }

    @Override
    protected Point extractParam(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        EnrolleeBuilder enrolleeBuilder = new EnrolleeBuilder();
        Enrollee enrollee = enrolleeBuilder
                .setFirstName(queryParameters.get(Fields.ENROLLEE_FIRST_NAME).getFirst())
                .setLastName(queryParameters.get(Fields.ENROLLEE_LAST_NAME).getFirst())
                .setEmail(queryParameters.get(Fields.ENROLLEE_EMAIL).getFirst())
                .setPassword(queryParameters.get(Fields.ENROLLEE_PASSWORD).getFirst())
                .setLogin(queryParameters.get(Fields.ENROLLEE_LOGIN).getFirst())
                .build();
        PointBuilder pointBuilder = new PointBuilder();
        pointBuilder.setEnrollee(enrollee);
        SubjectBuilder subjectBuilder = new SubjectBuilder();
        subjectBuilder.setFullName(queryParameters.get(Fields.SUBJECT_NAME).getFirst())
                .setSubjectType(new SubjectType("", queryParameters.get(Fields.SUBJECT_TYPE_NAME).getFirst()));
        pointBuilder.setSubject(subjectBuilder.build());
        pointBuilder.setPoint(Double.parseDouble(queryParameters.get(Fields.POINT_POINT).getFirst()));
        return pointBuilder.build();
    }
}
