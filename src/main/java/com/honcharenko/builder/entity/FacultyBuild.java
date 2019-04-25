package com.honcharenko.builder.entity;

import com.honcharenko.entity.Faculty;
import com.honcharenko.entity.Subject;

public class FacultyBuild {
    private Faculty faculty;

    public FacultyBuild() {
    }
    
    public FacultyBuild setAbbr(String abbr) {
        this.faculty.setAbbr(abbr);
        return this;
    }
    
    public FacultyBuild setFullName(String fullname) {
        this.faculty.setFullName(fullname);
        return this;
    }
    
    public FacultyBuild setBudgetCount(int budgetCount) {
        this.faculty.setBudgetCount(budgetCount);
        return this;
    }
    
    public FacultyBuild setTotalCount(int totalCount) {
        this.faculty.setTotalCount(totalCount);
        return this;
    }
    
    public FacultyBuild addSubject(Subject subject, double coefficient) {
        this.faculty.addSubject(subject, coefficient);
        return this;
    }

    public Faculty build() {
        return this.faculty;
    }
}
