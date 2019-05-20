package com.honcharenko.servlet;

import com.honcharenko.builder.entity.PointBuilder;
import com.honcharenko.entity.Point;
import com.honcharenko.service.impl.EnrolleeService;
import com.honcharenko.service.impl.PointService;
import com.honcharenko.service.impl.SubjectService;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class PointHandler extends BasicHandler<Point> {

    public PointHandler(DaoType daoType) {
        this.servletName = "point";
        this.idParamName = "pointId";
        this.service = new PointService(daoType);
        this.daoType = daoType;
    }

    @Override
    protected Point extractParam(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        PointBuilder pointBuilder = new PointBuilder();
        String first = queryParameters.get(Fields.POINT_SUBJECT_ID).getFirst();
        EnrolleeService enrolleeService = new EnrolleeService(this.daoType);
        SubjectService subjectService = new SubjectService(this.daoType);
        String point = queryParameters.get(Fields.POINT_POINT).getFirst();
        return pointBuilder
                .setSubject(subjectService.getById(first))
                .setId(queryParameters.get(Fields.POINT_ID).getFirst())
                .setPoint(Double.parseDouble(point))
                .setEnrollee(enrolleeService.getById(queryParameters.get(Fields.POINT_ENROLLEE_ID).getFirst()))
                .build();
    }

    @Override
    protected Point extractParamForUpdate(HttpServerExchange httpServerExchange) {
        String id = httpServerExchange.getQueryParameters().get(Fields.POINT_ID).getFirst();
        Point point = extractParam(httpServerExchange);
        point.setId(id);
        return point;
    }
}
