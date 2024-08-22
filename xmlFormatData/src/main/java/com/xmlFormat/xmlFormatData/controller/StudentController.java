package com.xmlFormat.xmlFormatData.controller;

import com.xmlFormat.xmlFormatData.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    //localhost:8080/userInfo?mediaType=xml
    //localhost:8080/userInfo?mediaType=json
    @GetMapping("/userInfo")
    public Student studentInfo()
    {
        Student s= new Student(1,"Vedant",2,"red");
        return s;
    }
}
