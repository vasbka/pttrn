package com.honcharenko.server.http;

import com.honcharenko.dao.DAO;
import com.honcharenko.entity.Enrollee;
import com.honcharenko.observer.Subscriber;
import com.honcharenko.observer.impl.DaoSubsciber;
import com.honcharenko.server.http.nosql.PointNosqlHandler;
import com.honcharenko.util.DaoManager;
import com.honcharenko.util.DaoType;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

public class  Server {
    public static void main(String[] args) {

        Subscriber subscriber = new DaoSubsciber();
        DAO daoByEntityType = new DaoManager(DaoType.MYSQL).getFactory().getDaoByEntityType(Enrollee.class);
        daoByEntityType.getDaoPublish().addSubscriber(subscriber);
        RoutingHandler add = Handlers
                .routing()
                .addAll(new EnrolleeHandler(DaoType.MYSQL).getHandler())
                .addAll(new FacultyHandler(DaoType.MYSQL).getHandler())
                .addAll(new PointNosqlHandler(DaoType.MYSQL).getHandler())
                .addAll(new SubjectHandler(DaoType.MYSQL).getHandler())
                .addAll(new EnrolleeHandler(DaoType.NOSQL).getHandler())
                .addAll(new FacultyHandler(DaoType.NOSQL).getHandler())
                .addAll(new PointNosqlHandler(DaoType.NOSQL).getHandler())
                .addAll(new SubjectHandler(DaoType.NOSQL).getHandler());
        Undertow.builder()
                .addHttpListener(27021, "localhost")
                .setHandler(add)
                .build()
                .start();
    }
}
