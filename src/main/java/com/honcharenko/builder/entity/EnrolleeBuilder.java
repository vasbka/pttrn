package com.honcharenko.builder.entity;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;

public class EnrolleeBuilder {
    private Enrollee enrollee;

    public EnrolleeBuilder() {
        enrollee = new Enrollee();
    }

    public EnrolleeBuilder setId(String id) {
        enrollee.setId(id);
        return this;
    }

    public EnrolleeBuilder setFirstName(String firstName) {
        enrollee.setFirstName(firstName);
        return this;
    }

    public EnrolleeBuilder setLastName(String lastName) {
        enrollee.setLastName(lastName);
        return this;
    }

    public EnrolleeBuilder setPassword(String password) {
        enrollee.setPassword(password);
        return this;
    }

    public EnrolleeBuilder setEmail(String email) {
        enrollee.setEmail(email);
        return this;
    }

    public EnrolleeBuilder setLogin(String login) {
        enrollee.setLogin(login);
        return this;
    }

    public EnrolleeBuilder addPoint(Point point) {
        enrollee.addPoints(point);
        return this;
    }

    public Enrollee build() {
        return this.enrollee;
    }
}
