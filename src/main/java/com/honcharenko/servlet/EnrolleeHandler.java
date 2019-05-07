package com.honcharenko.servlet;

import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.observer.Subscriber;
import com.honcharenko.observer.impl.DaoPublisher;
import com.honcharenko.observer.impl.DaoSubsciber;
import com.honcharenko.service.impl.EnrolleeService;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;

import java.util.Deque;
import java.util.Map;

public class EnrolleeHandler extends BasicHandler<Enrollee> {
    public EnrolleeHandler(DaoType daoType) {
        this.servletName = "enrollee";
        this.idParamName = "enrolleeId";
        this.service = new EnrolleeService(daoType);


    }

    protected Enrollee extractParamForUpdate(HttpServerExchange httpServerExchange) {
        String first = httpServerExchange.getQueryParameters().get(Fields.ENROLLEE_ID).getFirst();
        Enrollee enrollee = extractParam(httpServerExchange);
        enrollee.setId(Integer.valueOf(first));
        return enrollee;
    }

    protected Enrollee extractParam(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        EnrolleeBuilder enrolleeBuilder = new EnrolleeBuilder();
        return enrolleeBuilder.setEmail(queryParameters.get(Fields.ENROLLEE_EMAIL).getFirst())
                                    .setFirstName(queryParameters.get(Fields.ENROLLEE_FIRST_NAME).getFirst())
                                    .setLastName(queryParameters.get(Fields.ENROLLEE_LAST_NAME).getFirst())
                                    .setLogin(queryParameters.get(Fields.ENROLLEE_LOGIN).getFirst())
                                    .setPassword(queryParameters.get(Fields.ENROLLEE_PASSWORD).getFirst())
                                .build();
    }

}
