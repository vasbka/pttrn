package com.honcharenko.servlet;

import com.google.gson.Gson;
import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Property;
import com.honcharenko.service.impl.EnrolleeService;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.Handlers;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.HttpString;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class EnrolleeHandler {

    private static final String ENROLLEE_SERVLET_NAME =  "enrollee";
    private static final String ENROLLEE_ID_PARAM = "enrolleeId";
    private static EnrolleeService enrolleeService;
    private static Gson gson = new Gson();

    public static RoutingHandler getEnrolleeHandler(DaoType daoType) {
        enrolleeService = new EnrolleeService(daoType);
        return Handlers
                .routing()
                .get("/" + ENROLLEE_SERVLET_NAME+ "/{" + ENROLLEE_ID_PARAM + "}", httpServerExchange -> {
                    String enrolleeId = httpServerExchange.getQueryParameters().get(ENROLLEE_ID_PARAM).getFirst();
                    if (enrolleeId == null) {
                        httpServerExchange.setStatusCode(400);
                        httpServerExchange.getResponseSender().send("Enrollee id should not be empty.");
                        return;
                    }
                    Enrollee enrollee = enrolleeService.getById(Integer.valueOf(enrolleeId));
                    if (enrollee == null) {
                        send(httpServerExchange, gson.toJson("{}"));
                        return;
                    }
                    send(httpServerExchange, gson.toJson(enrollee));
                })
                .get("/" + ENROLLEE_SERVLET_NAME, httpServerExchange -> {
                    List<Enrollee> all = enrolleeService.getAll();
                    send(httpServerExchange, gson.toJson(all));
                })
                .post("/" + ENROLLEE_SERVLET_NAME + "/{" + ENROLLEE_ID_PARAM + "}", httpServerExchange -> {
                    Enrollee enrollee = extractParamToEnrollee(httpServerExchange);
                    send(httpServerExchange, gson.toJson(enrollee));
                })
                .put("/" + ENROLLEE_SERVLET_NAME + "/add", httpServerExchange -> {
                    Enrollee enrolle = extractParamToEnrollee(httpServerExchange);
                    Enrollee add = enrolleeService.add(enrolle);
                    send(httpServerExchange, gson.toJson(add));
                })
                .post("/" + ENROLLEE_SERVLET_NAME + "/delete/{" + ENROLLEE_ID_PARAM + "}", httpServerExchange -> {
                    String enrolleeId = httpServerExchange.getQueryParameters().get(ENROLLEE_ID_PARAM).getFirst();
                    Enrollee add = enrolleeService.removeById(Integer.valueOf(enrolleeId));
                    send(httpServerExchange, gson.toJson(add));
                })
                .get("/" + ENROLLEE_SERVLET_NAME + "/byParams", httpServerExchange -> {
                    List<Property> properties = new ArrayList<>();
                    httpServerExchange.getQueryParameters().forEach((key, value) -> properties.add(new Property(key, value.getFirst())));
                    List<Enrollee> byProperty = enrolleeService.getByProperty(properties);
                    send(httpServerExchange, gson.toJson(byProperty));
                });
    }

    private static Enrollee extractParamToEnrollee(HttpServerExchange httpServerExchange) {
        Map<String, Deque<String>> queryParameters = httpServerExchange.getQueryParameters();
        EnrolleeBuilder enrolleeBuilder = new EnrolleeBuilder();
        return enrolleeBuilder.setEmail(queryParameters.get(Fields.ENROLLEE_EMAIL).getFirst())
                                    .setFirstName(queryParameters.get(Fields.ENROLLEE_FIRST_NAME).getFirst())
                                    .setLastName(queryParameters.get(Fields.ENROLLEE_LAST_NAME).getFirst())
                                    .setLogin(queryParameters.get(Fields.ENROLLEE_LOGIN).getFirst())
                                    .setPassword(queryParameters.get(Fields.ENROLLEE_PASSWORD).getFirst())
                                .build();
    }

    private static void send(HttpServerExchange httpServerExchange, String text) {
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type".getBytes()), "text/json; charset=utf-8");
        httpServerExchange.getResponseSender().send(text, java.nio.charset.Charset.forName("UTF-8"));
    }

}
