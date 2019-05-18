package com.honcharenko.entity;

import java.util.HashMap;

public class Faculty implements EntityId {
    private String id;
    private String abbr;
    private String fullName;
    private int budgetCount;
    private int totalCount;
    private HashMap<Subject, Double> subjects;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBudgetCount() {
        return budgetCount;
    }

    public void setBudgetCount(int budgetCount) {
        this.budgetCount = budgetCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public HashMap<Subject, Double> getSubjects() {
        return subjects;
    }

    public void setSubjects(HashMap<Subject, Double> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(Subject subject, double coefficient) {
        subjects.put(subject, coefficient);
    }
}
