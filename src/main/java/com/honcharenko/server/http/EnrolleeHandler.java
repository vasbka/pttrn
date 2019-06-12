package com.honcharenko.server.http;

import com.google.gson.Gson;
import com.honcharenko.builder.entity.EnrolleeBuilder;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.memento.EnrolleeCaretaker;
import com.honcharenko.memento.impl.EnrolleeSnapshot;
import com.honcharenko.service.impl.EnrolleeService;
import com.honcharenko.service.impl.SubjectPopularityService;
import com.honcharenko.util.DaoType;
import com.honcharenko.util.Fields;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import org.bson.Document;

import java.util.*;

public class EnrolleeHandler extends BasicHandler<Enrollee> {
    private EnrolleeCaretaker enrolleeCaretaker;

    public EnrolleeHandler(DaoType daoType) {
        this.servletName = "enrollee";
        this.idParamName = "enrolleeId";
        this.service = new EnrolleeService(daoType);
        this.enrolleeCaretaker = EnrolleeCaretaker.getInstance();
        this.daoType = daoType;
    }

    protected Enrollee extractParamForUpdate(HttpServerExchange httpServerExchange) {
        String first = httpServerExchange.getQueryParameters().get(Fields.ENROLLEE_ID).getFirst();
        Enrollee enrollee = extractParam(httpServerExchange);
        enrollee.setId(first);
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


    @Override
    public RoutingHandler getHandler() {
        RoutingHandler handler = super.getHandler();
        handler.get("/" + daoType + "/" + servletName + "/createSnapshot/{" + idParamName + "}", httpServerExchange -> {
            String first = httpServerExchange.getQueryParameters().get(idParamName).getFirst();
            EnrolleeSnapshot snapshot = service.getById(first).createSnapshot();
            send(httpServerExchange, String.valueOf(first));
        })
        .get("/" + daoType + "/" + servletName + "/snapshots/getAllSnapshots", httpServerExchange -> {
            send(httpServerExchange, new Gson().toJson(enrolleeCaretaker));
        })
        .get("/" + daoType + "/" + servletName + "/snapshots/reset", httpServerExchange -> {
            Enrollee restore = enrolleeCaretaker.restore();
            service.update(restore);
            send(httpServerExchange, new Gson().toJson(restore));
        })
        .get("/" + daoType + "/" + servletName + "/snapshots/current", httpServerExchange -> {
            send(httpServerExchange, new Gson().toJson(enrolleeCaretaker.getCurrentSnap()));
        })
        .get("/" + daoType + "/" + servletName + "/snapshots/moveBack", httpServerExchange -> {
            send(httpServerExchange, new Gson().toJson(enrolleeCaretaker.moveBack()));
        })
        .get("/" + daoType + "/" + servletName + "/snapshots/moveForward", httpServerExchange -> {
            send(httpServerExchange, new Gson().toJson(enrolleeCaretaker.moveForward()));
        })
        .get("/" + daoType + "/testReplic", httpServerExchange -> {
            Enrollee enrollee = new Enrollee();
            enrollee.setLogin("login");
            enrollee.setPassword("pwd");
            enrollee.setLastName("lastname");
            enrollee.setFirstName("first");
            enrollee.setEmail("emnail@emial.com");
            long start = System.currentTimeMillis();

            service.add(enrollee);
        })
        .get("/" + daoType + "/aggregation", httpServerExchange -> {
            List<Document> documents = Arrays.asList(
                    new Document("$unwind", "$points"),
                    new Document("$project",
                            new Document("points.subjectName", 1)
                                .append("enrolleeEmail", 1)
                                .append("count", new Document("$add", 1))),
                    new Document("$group", new Document("_id", new Document("points", "$points").append("email", "$enrolleeEmail")).append("cnt", new Document("$sum", "$count")))
            );
            send(httpServerExchange, new Gson().toJson(new SubjectPopularityService().getByAggregations(documents)));
        });
        return handler;
    }
}
