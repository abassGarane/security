package com.abass.security.config;


public enum AppUserPermissions {
    STUDENT_READ("student:read"),
    STUDENT_WRITE("student:write"),
    COURSE_WRITE("course:write"),
    COURSE_READ("course:read"),

    TEACHER_READ("teacher:read"),
    TEACHER_WRITE("teacher:write");
    private final String permission;

    AppUserPermissions(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

}
