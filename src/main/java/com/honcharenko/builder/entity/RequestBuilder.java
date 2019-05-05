package com.honcharenko.builder.entity;

import com.honcharenko.entity.Request;

public class RequestBuilder {
    private Request request;

    RequestBuilder() {
        this.request = new Request();
    }

    public RequestBuilder setId(int id){
        this.request.setId(id);
        return this;
    }
    
    public RequestBuilder setEnrolleeId(int enrolleeId) {
        this.request.setEnrolleeId(enrolleeId);
        return this;
    }

    public RequestBuilder setFacultyId(int facultyId) {
        this.request.setFacultyId(facultyId);
        return this;
    }

    public RequestBuilder setAverageScore(double averageScore) {
        this.request.setAverageScore(averageScore);
        return this;
    }

    public Request build() {
        return this.request;
    }

}
