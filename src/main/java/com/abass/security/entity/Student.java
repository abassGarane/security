package com.abass.security.entity;

public class Student {
    private final String name;
    private final String admissionNumber;

    public Student(String name, String admissionNumber) {
        this.name = name;
        this.admissionNumber = admissionNumber;
    }

    public String getName() {
        return name;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }
}
