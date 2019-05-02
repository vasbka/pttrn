package com.honcharenko.builder.entity;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

public class PointBuilder {
    private Point point;

    public PointBuilder() {
        point = new Point();
    }

    public PointBuilder setEnrollee(Enrollee enrolle) {
        point.setEnrollee(enrolle);
        return this;
    }
    
    public PointBuilder setSubject(Subject subject) {
        point.setSubject(subject);
        return this;
    }

    public PointBuilder setPoint(double point) {
        this.point.setPoint(point);
        return this;
    }

    public PointBuilder setId(int id) {
        point.setId(id);
        return this;
    }

    public Point build() {
        return this.point;
    }
}
