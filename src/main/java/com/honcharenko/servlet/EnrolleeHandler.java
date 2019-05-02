package com.honcharenko.servlet;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.factory.AbstractDaoFactory;
import com.honcharenko.factory.MySqlFactory;
import com.honcharenko.factory.NoSqlFactory;
import com.honcharenko.service.impl.EnrolleeService;
import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

public class EnrolleeHandler {

    private static final String ENROLLEE_SERVLET_NAME =  "enrollee";
    private static final String ENROLLEE_ID_PARAM = "enrolleeId";
    private static EnrolleeService enrolleeService;

    public static RoutingHandler getEnrolleeHandler(DaoType daoType) {
        enrolleeService = new EnrolleeService();
        return Handlers
                .routing()
                .get("/" + ENROLLEE_SERVLET_NAME+ "/{" + ENROLLEE_ID_PARAM + "}", httpServerExchange -> {
                    String enrolleeId = httpServerExchange.getQueryParameters().get(ENROLLEE_ID_PARAM).getFirst();

                    if (enrolleeId == null) {
                        httpServerExchange.setStatusCode(400);
                        httpServerExchange.getResponseSender().send("Enrollee id should not be empty.");
                        return;
                    }

                    enrolleeService.getAll();
                    httpServerExchange.getResponseSender().send("ok");
                });
    }

    private HttpHandler getHandl(HttpServerExchange exchange) {

        return null;
    }

}
