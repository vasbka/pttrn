package com.honcharenko.builder.entity;

import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Subject;

public class FacultyBuilder {
    private Faculty faculty;

    public FacultyBuilder() {
        faculty = new Faculty();
    }

    public FacultyBuilder setId(int id) {
        this.faculty.setId(id);
        return this;
    }
    
    public FacultyBuilder setAbbr(String abbr) {
        this.faculty.setAbbr(abbr);
        return this;
    }
    
    public FacultyBuilder setFullName(String fullname) {
        this.faculty.setFullName(fullname);
        return this;
    }
    
    public FacultyBuilder setBudgetCount(int budgetCount) {
        this.faculty.setBudgetCount(budgetCount);
        return this;
    }
    
    public FacultyBuilder setTotalCount(int totalCount) {
        this.faculty.setTotalCount(totalCount);
        return this;
    }
    
    public FacultyBuilder addSubject(Subject subject, double coefficient) {
        this.faculty.addSubject(subject, coefficient);
        return this;
    }

    public Faculty build() {
        return this.faculty;
    }
}
