package com.honcharenko.server.http;

import com.honcharenko.builder.entity.SubjectBuilder;
import com.honcharenko.entity.Subject;
import com.honcharenko.service.impl.SubjectService;
import com.honcharenko.service.impl.SubjectTypeService;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class SubjectHandler extends BasicHandler<Subject> {

    public SubjectHandler(DaoType daoType) {
        this.servletName = "subject";
        this.idParamName = "subjectId";
        this.service = new SubjectService(daoType);
        this.daoType = daoType;
    }

    @Override
    protected Subject extractParam(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        SubjectTypeService subjectTypeService = new SubjectTypeService(this.daoType);
        SubjectBuilder subjectBuilder = new SubjectBuilder();
        Subject build = subjectBuilder
                .setSubjectType(subjectTypeService.getById(queryParameters.get(Fields.SUBJECT_SUBJECT_TYPE_ID).getFirst()))
                .setFullName(queryParameters.get(Fields.SUBJECT_NAME).getFirst())
                .setId(queryParameters.get(Fields.SUBJECT_ID).getFirst())
                .build();
        return build;
    }

    @Override
    protected Subject extractParamForUpdate(HttpServerExchange httpServerExchange) {
        String id = httpServerExchange.getQueryParameters().get(Fields.SUBJECT_ID).getFirst();
        Subject subject = extractParam(httpServerExchange);
        subject.setId(id);
        return subject;
    }
}
