package com.honcharenko.entity;

import java.util.Objects;

public class Request implements EntityId {
    private String id;
    private int enrolleeId;
    private int FacultyId;
    private double averageScore;

    public Request() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getEnrolleeId() {
        return enrolleeId;
    }

    public void setEnrolleeId(int enrolleeId) {
        this.enrolleeId = enrolleeId;
    }

    public int getFacultyId() {
        return FacultyId;
    }

    public void setFacultyId(int facultyId) {
        FacultyId = facultyId;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return id == request.id &&
                enrolleeId == request.enrolleeId &&
                FacultyId == request.FacultyId &&
                Double.compare(request.averageScore, averageScore) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, enrolleeId, FacultyId, averageScore);
    }

    @Override
    public String toString() {
        return "RequestBuilder{" +
                "id=" + id +
                ", enrolleeId=" + enrolleeId +
                ", FacultyId=" + FacultyId +
                ", averageScore=" + averageScore +
                '}';
    }
}
