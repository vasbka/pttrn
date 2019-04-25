package com.honcharenko.builder.entity;

import com.honcharenko.entity.Enrollee;

public class EnrolleeBuilder {
    private Enrollee enrollee;

    public EnrolleeBuilder() {
    }

    public EnrolleeBuilder setFirstName(String firstName) {
        enrollee.setFirstName(firstName);
        return this;
    }

    public EnrolleeBuilder setLastName(String lastName) {
        enrollee.setLastName(lastName);
        return this;
    }

    public EnrolleeBuilder setCity(String city) {
        enrollee.setCity(city);
        return this;
    }

    public EnrolleeBuilder setAge(int age) {
        enrollee.setAge(age);
        return this;
    }
    
    public EnrolleeBuilder setPassword(String password) {
        enrollee.setPassword(password);
        return this;
    }

    public EnrolleeBuilder addEmail(String email) {
        enrollee.addEmail(email);
        return this;
    }

    public EnrolleeBuilder setLogin(String login) {
        enrollee.setLogin(login);
        return this;
    }

    public Enrollee build() {
        return this.enrollee;
    }
}
