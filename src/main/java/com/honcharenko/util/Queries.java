package com.honcharenko.util;

public abstract class Queries {
    public static final String ENROLLEE_INSERT = "NULL, ?, ?, ?, ?, ?";
    public static final String ENROLLEE_UPDATE = "enrolleeFirstName = ?, enrolleeLastName = ?, enrolleeEmail = ?, enrolleeLogin = ?, enrolleePassword =?";
    public static final String FACULTY_INSERT = "NULL, ?, ?, ?";
    public static final String FACULTY_UPDATE = "facultyName = ?, facultyGeneralCount = ?, facultyTotalCount = ?";
}
