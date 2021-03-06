package com.honcharenko.entity;

import com.honcharenko.memento.Snapshot;
import com.honcharenko.memento.impl.EnrolleeSnapshot;

import java.util.ArrayList;
import java.util.List;

public class Enrollee implements EntityId {
    private String id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String email;
    private List<Point> points;

    public Enrollee() {
        points = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    public void addPoints(Point point) {
        this.points.add(point);
    }

    public EnrolleeSnapshot createSnapshot() {
        return new EnrolleeSnapshot(this, id, firstName, lastName, login, password, email, points);
    }

    @Override
    public String toString() {
        return "Enrollee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                '}';
    }
}
