package com.honcharenko.servlet;

import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

public class Server {
    public static void main(String[] args) {
        RoutingHandler add = Handlers
                .routing()
                .addAll(EnrolleeHandler.getEnrolleeHandler(DaoType.MYSQL));
//                .add("GET", "/enrollee/{enrolleeId}", exchange -> {
//                    exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
//                    String itemId2 = exchange.getQueryParameters().get("itemId").getFirst();
//                    exchange.getResponseSender().send(itemId2);
//                });
        Undertow.builder()
                .addHttpListener(8080, "localhost")
                .setHandler(add)
                .build()
                .start();
    }
}
