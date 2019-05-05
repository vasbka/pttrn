package com.honcharenko.util;

public abstract class Queries {
    public static final String SELECT_ENROLLEE_WITH_POINTS = "SELECT enrollee.*, enrolleeSubject.id as pointId, enrolleeSubject.subjectId, enrolleeSubject.point " +
                                                                "FROM enrollee " +
                                                                "LEFT JOIN enrolleeSubject ON enrolleeId = enrollee.id " +
                                                                "UNION " +
                                                                "SELECT enrollee.*, enrolleeSubject.id as pointId, enrolleeSubject.subjectId, enrolleeSubject.point " +
                                                                "FROM enrollee " +
                                                                "RIGHT JOIN enrolleeSubject ON enrolleeId = enrollee.id ";
    public static final String INSERT_INTO_ENROLLEE = "INSERT INTO enrollee(firstName, lastName, login, password, email) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ENROLLEE_WITH_POINTS_BY_ID = SELECT_ENROLLEE_WITH_POINTS + " WHERE enrollee.id = ?";
    public static final String DELETE_ENROLLEE_BY_ID = "DELETE FROM enrollee WHERE id = ?";
    public static final String UPDATE_ENROLLEE_BY_ID = "UPDATE enrollee SET firstName = ?, lastName = ?, email = ?, login = ?, password =? WHERE id = ?";
}
