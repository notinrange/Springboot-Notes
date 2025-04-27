package com.example.securityApp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.securityApp.model.Student;

import jakarta.servlet.http.HttpServletRequest;





@RestController
public class StudentController {
    private List<Student> students = new ArrayList<>(List.of(
        new Student(1,"Rahul",59),
        new Student(2,"Rahul",49)
    ));
    
    @GetMapping("/students")
    public List<Student> getStudents(){
        return students;
    }

    // Http is Stateless
    // Post,Put,Patch req are doing change in db for that we need crsf token also
    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        System.out.println(request.getAttribute("_csrf"));
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }

}
