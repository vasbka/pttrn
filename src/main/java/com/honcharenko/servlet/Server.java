package com.honcharenko.servlet;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.observer.Subscriber;
import com.honcharenko.observer.impl.DaoSubsciber;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

public class Server {
    public static void main(String[] args) {

        Subscriber subscriber = new DaoSubsciber();
        DAO daoByEntityType = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Enrollee.class);
        daoByEntityType.getDaoPublish().addSubscriber(subscriber);
        RoutingHandler add = Handlers
                .routing()
                .addAll(new EnrolleeHandler(DaoType.NOSQL).getHandler())
                .addAll(new FacultyHandler(DaoType.MYSQL).getHandler());
        Undertow.builder()
                .addHttpListener(27019, "localhost")
                .setHandler(add)
                .build()
                .start();
    }
}
