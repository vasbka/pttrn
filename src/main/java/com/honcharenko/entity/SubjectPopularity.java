package com.honcharenko.entity;

import java.util.Objects;

public class SubjectPopularity implements EntityId {
    private String id;
    private String subjectName;
    private long count;

    public SubjectPopularity() {
    }

    public SubjectPopularity(String id, long count) {
        this.id = id;
        this.count = count;
    }

    public SubjectPopularity(String id, String subjectName, long count) {
        this.id = id;
        this.subjectName = subjectName;
        this.count = count;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubjectPopularity that = (SubjectPopularity) o;
        return count == that.count &&
                Objects.equals(id, that.id) &&
                Objects.equals(subjectName, that.subjectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subjectName, count);
    }

    @Override
    public String toString() {
        return "SubjectPopularity{" +
                "id='" + id + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", count=" + count +
                '}';
    }
}
