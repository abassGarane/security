package com.abass.security.controllers;

import com.abass.security.entity.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class MainController {
    private static final List<Student> studenList = Arrays.asList(
            new Student("abass diyad","j31-ol-gar-7153-2019"),
            new Student("salah diyad","511"),
            new Student("abdiwahab","1222")
    );
    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") String admissionNumber){
        return studenList.stream()
                .filter(student -> student.getAdmissionNumber().equals(admissionNumber))
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("Student not found with adm number :" + " " + admissionNumber));
    }

    @GetMapping("/administration")
    public String administration(){
        System.out.println("called management url");
        return "management";
    }
    @GetMapping("/administration/teachers")
    public String administrationTeachers(){
        System.out.println("called management url");
        return "teachers";
    }

    @GetMapping("/classes")
    @PreAuthorize("hasAuthority('student:read')")
    public String getClasses(){
        return "classes";
    }
    @GetMapping("/library")
    @PreAuthorize("hasAnyRole('ROLE_TEACHER','ROLE_PRINCIPAL')")
    public String getLibrary(){
        return "library";
    }
}
