package com.honcharenko.builder.entity;

import com.honcharenko.entity.Subject;

public class SubjectBuilder {
    private Subject subject;

    public SubjectBuilder() {
    }
    
    public SubjectBuilder setId(int id) {
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

    public Subject build() {
        return this.subject;
    }
}
