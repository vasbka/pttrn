//package com.honcharenko.servlet;
//
//import com.google.gson.Gson;
//import com.honcharenko.entity.Enrollee;
//import com.honcharenko.service.impl.EnrolleeService;
//import com.honcharenko.util.DaoType;
//import io.undertow.Handlers;
//import io.undertow.server.HttpServerExchange;
//import io.undertow.server.RoutingHandler;
//import io.undertow.util.HttpString;
//
//import java.util.List;
//
//public class FacultyHandler {
//
//    private static final String FACULTY_SERVLET_NAME =  "faculty";
//    private static final String FACULTY_ID_PARAM = "facultyId";
//    private static EnrolleeService enrolleeService;
//    private static Gson gson = new Gson();
//
//
//    public static RoutingHandler getEnrolleeHandler(DaoType daoType) {
//        enrolleeService = new EnrolleeService(daoType);
//        return Handlers
//                .routing()
//                .get("/" + FACULTY_SERVLET_NAME + "/{" + FACULTY_ID_PARAM + "}", httpServerExchange -> {
//                    String enrolleeId = httpServerExchange.getQueryParameters().get(FACULTY_ID_PARAM).getFirst();
//                    if (enrolleeId == null) {
//                        httpServerExchange.setStatusCode(400);
//                        httpServerExchange.getResponseSender().send("Enrollee id should not be empty.");
//                        return;
//                    }
//                    Enrollee enrollee = enrolleeService.getById(Integer.valueOf(enrolleeId));
//                    if (enrollee == null) {
//                        send(httpServerExchange, gson.toJson("{}"));
//                        return;
//                    }
//                    send(httpServerExchange, gson.toJson(enrollee));
//                })
//                .get("/" + FACULTY_SERVLET_NAME, httpServerExchange -> {
//                    List<Enrollee> all = enrolleeService.getAll();
//                    send(httpServerExchange, gson.toJson(all));
//                })
//                .post("/" + FACULTY_SERVLET_NAME + "/{" + FACULTY_ID_PARAM + "}", httpServerExchange -> {
//                    Enrollee enrollee = extractParamToEnrollee(httpServerExchange);
//                    send(httpServerExchange, gson.toJson(enrollee));
//                })
//                .put("/" + FACULTY_SERVLET_NAME + "/add", httpServerExchange -> {
//                    Enrollee enrolle = extractParamToEnrollee(httpServerExchange);
//                    Enrollee add = enrolleeService.add(enrolle);
//                    send(httpServerExchange, new Gson().toJson(add));
//                });
//    }
//
//
//    private static void send(HttpServerExchange httpServerExchange, String text) {
//        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type".getBytes()), "text/json; charset=utf-8");
//        httpServerExchange.getResponseSender().send(text, java.nio.charset.Charset.forName("UTF-8"));
//    }
//}
