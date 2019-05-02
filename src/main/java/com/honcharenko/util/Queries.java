package com.honcharenko.util;

public abstract class Queries {
    public static final String SELECT_ENROLLEE_WITH_POINTS = "SELECT enrollee.*, enrolleeSubject.id as pointId, enrolleeSubject.subjectId, enrolleeSubject.point " +
                                                                "FROM enrollee " +
                                                                "INNER JOIN enrolleeSubject ON enrolleeId = enrollee.id";
    public static final String INSERT_INTO_ENROLLEE = "INSERT INTO enrollee VALUES (?, ?, ?, ?, ?)";
}
