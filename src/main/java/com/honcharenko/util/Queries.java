package com.honcharenko.util;

public abstract class Queries {
    public static final String ENROLLEE_INSERT = "NULL, ?, ?, ?, ?, ?";
    public static final String ENROLLEE_UPDATE = "enrolleeFirstName = ?, enrolleeLastName = ?, enrolleeEmail = ?, enrolleeLogin = ?, enrolleePassword =?";
    public static final String FACULTY_INSERT = "NULL, ?, ?, ?";
    public static final String FACULTY_UPDATE = "facultyName = ?, facultyGeneralCount = ?, facultyTotalCount = ?";
    public static final String POINT_INSERT = "NULL, ?, ?, ?";
    public static final String POINT_UPDATE = "enrolleeSubjectEnrolleeId = ?, enrolleeSubjectSubjectId = ?, enrolleeSubjectPoint = ?";
    public static final String SUBJECT_INSERT = "NULL, ?, ?";
    public static final String SUBJECT_UPDATE = Fields.SUBJECT_NAME + " = ?, " + Fields.SUBJECT_SUBJECT_TYPE_ID + " = ? ";
    public static final String SUBJECT_TYPE_INSERT = "NULL, ?, ?";
    public static final String SUBJECT_TYPE_UPDATE = Fields.SUBJECT_TYPE_NAME + " = ?";

}
