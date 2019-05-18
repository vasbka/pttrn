package com.honcharenko.entity;

public class Point implements EntityId {
    private String id;
    private Subject subject;
    private Enrollee enrollee;
    private double point;

    public Point() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Enrollee getEnrollee() {
        return enrollee;
    }

    public void setEnrollee(Enrollee enrollee) {
        this.enrollee = enrollee;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", subject=" + subject +
                ", enrollee=" + enrollee +
                ", point=" + point +
                '}';
    }
}
