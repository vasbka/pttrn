package com.honcharenko.servlet;

import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

public class Server {
    public static void main(String[] args) {
        RoutingHandler add = Handlers
                .routing()
                .addAll(new EnrolleeHandler(DaoType.MYSQL).getHandler())
                .addAll(new FacultyHandler(DaoType.MYSQL).getHandler());
        Undertow.builder()
                .addHttpListener(8090, "localhost")
                .setHandler(add)
                .build()
                .start();
    }
}
