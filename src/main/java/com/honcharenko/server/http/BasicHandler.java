package com.honcharenko.server.http;

import com.google.gson.Gson;
import com.honcharenko.entity.Property;
import com.honcharenko.service.Service;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;
import io.undertow.util.HttpString;

import java.util.ArrayList;
import java.util.List;

public abstract class BasicHandler<E> {
    protected DaoType daoType;
    protected Service<E> service;

    private static final String ADD = "add";
    private static final String DELETE = "delete";
    private static final String UPDATE = "update";
    private static final String BY_PARAMETERS = "byParams";
    private static Gson gson = new Gson();
    protected String servletName;
    protected String idParamName;

    public RoutingHandler getHandler() {
        return Handlers.routing()
                    .get("/" + daoType + "/" + servletName + "/{" + idParamName + "}", httpServerExchange -> {
                        String id = httpServerExchange.getQueryParameters().get(idParamName).getFirst();
                        if (id == null) {
                            httpServerExchange.setStatusCode(400);
                            httpServerExchange.getResponseSender().send(servletName.toLowerCase()
                                    + " id should not be empty.");
                            return;
                        }
                        E object = service.getById(id);
                        if (object == null) {
                            send(httpServerExchange, "{}");
                            return;
                        }
                        send(httpServerExchange, gson.toJson(object));
                    })
                .get("/" + daoType + "/" + servletName, httpServerExchange -> {
                    List<E> all = service.getAll();
                    send(httpServerExchange, gson.toJson(all));
                })
                .get("/" + daoType + "/" + servletName + "/migrate", httpServerExchange -> this.service.migrate(DaoManager.getDaoToMigrate(daoType)))
                .put("/" + daoType + "/" + servletName + "/" + ADD, httpServerExchange -> {
                    E e = extractParam(httpServerExchange);
                    E add = service.add(e);
                    send(httpServerExchange, gson.toJson(add));
                })
                .post("/" + daoType + "/" + servletName + "/" + DELETE + "/{" +idParamName + "}", httpServerExchange -> {
                    String enrolleeId = httpServerExchange.getQueryParameters().get(idParamName).getFirst();
                    E removed = service.removeById(enrolleeId);
                    send(httpServerExchange, gson.toJson(removed));
                })
                .get("/" + daoType + "/" + servletName + "/" + BY_PARAMETERS, httpServerExchange -> {
                    List<Property> properties = new ArrayList<>();
                    httpServerExchange.getQueryParameters().forEach((key, value)
                            -> properties.add(new Property(key, value.getFirst())));
                    List<E> byProperty = service.getByProperty(properties);
                    send(httpServerExchange, gson.toJson(byProperty));
                })
                .post("/" + daoType + "/" + servletName + "/" + UPDATE, httpServerExchange -> {
                    E object = extractParamForUpdate(httpServerExchange);
                    E update = service.update(object);
                    send(httpServerExchange, gson.toJson(update));
                });
    }


    protected static void send(HttpServerExchange httpServerExchange, String text) {
        httpServerExchange.getResponseHeaders().add(new HttpString("Content-Type".getBytes()), "text/json; charset=utf-8");
        httpServerExchange.getResponseSender().send(text, java.nio.charset.Charset.forName("UTF-8"));
    }

    protected abstract E extractParam(HttpServerExchange httpServerExchange);

    protected abstract E extractParamForUpdate(HttpServerExchange httpServerExchange);

}
