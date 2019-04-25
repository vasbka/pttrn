package com.honcharenko.builder.entity;

import com.honcharenko.entity.Enrollee;
import com.honcharenko.entity.Point;
import com.honcharenko.entity.Subject;

public class PointBuilder {
    private Point point;

    public PointBuilder() {
    }

    public PointBuilder setEnrollee(Enrollee enrolle) {
        point.setEnrollee(enrolle);
        return this;
    }
    
    public PointBuilder setSubject(Subject subject) {
        point.setSubject(subject);
        return this;
    }

    public Point build() {
        return this.point;
    }
}
