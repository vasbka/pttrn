package com.honcharenko.servlet;

import com.honcharenko.builder.entity.FacultyBuilder;
import com.honcharenko.entity.Faculty;
import com.honcharenko.service.impl.FacultyService;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class FacultyHandler extends BasicHandler<Faculty>{

    public FacultyHandler(DaoType daoType) {
        this.servletName = "faculty";
        this.idParamName = "facultyId";
        this.service = new FacultyService(daoType);
    }

    @Override
    protected Faculty extractParam(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        FacultyBuilder facultyBuilder = new FacultyBuilder();
        facultyBuilder.setFullName(queryParameters.get(Fields.FACULTY_NAME).getFirst())
                .setTotalCount(Integer.valueOf(queryParameters.get(Fields.FACULTY_GENERAL_COUNT).getFirst()))
                .setBudgetCount(Integer.valueOf(queryParameters.get(Fields.FACULTY_BUDGET_COUNT).getFirst()));
        return facultyBuilder.build();
    }

    @Override
    protected Faculty extractParamForUpdate(HttpServerExchange httpServerExchange) {
        Faculty faculty = extractParam(httpServerExchange);
        faculty.setId(Integer.valueOf(httpServerExchange.getQueryParameters().get(idParamName).getFirst()));
        return faculty;
    }
}
