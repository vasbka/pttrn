package com.honcharenko.memento.impl;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.memento.Snapshot;

import java.util.List;

public class EnrolleeSnapshot implements Snapshot<Enrollee> {
    private Enrollee enrollee;
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private List<Point> points;

    public EnrolleeSnapshot(Enrollee enrollee, int id, String firstName, String lastName, String login, String password, String email, List<Point> points) {
        this.enrollee = enrollee;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.points = points;
    }

    @Override
    public Enrollee restore() {
        this.enrollee.setPassword(password);
        this.enrollee.setId(id);
        this.enrollee.setLogin(login);
        this.enrollee.setEmail(email);
        this.enrollee.setFirstName(firstName);
        this.enrollee.setLastName(lastName);
        return this.enrollee;
    }
}
