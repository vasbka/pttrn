package com.honcharenko.builder.entity;

import com.honcharenko.entity.Subject;
import com.honcharenko.entity.SubjectType;

public class SubjectBuilder {
    private Subject subject;

    public SubjectBuilder() {
        subject = new Subject();
    }
    
    public SubjectBuilder setId(String id) {
        subject.setId(id);
        return this;
    }

    public SubjectBuilder setShortName(String shortName) {
        subject.setShortName(shortName);
        return this;
    }

    public SubjectBuilder setFullName(String fullName) {
        subject.setFullName(fullName);
        return this;
    }

    public SubjectBuilder setSubjectType(SubjectType subjectType) {
        subject.setSubjectType(subjectType);
        return this;
    }

    public Subject build() {
        return this.subject;
    }
}
