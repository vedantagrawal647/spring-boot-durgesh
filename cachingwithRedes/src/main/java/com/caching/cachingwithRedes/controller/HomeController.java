package com.caching.cachingwithRedes.controller;

import com.caching.cachingwithRedes.entity.Student;
import com.caching.cachingwithRedes.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;



    //without cache
    @GetMapping("/home")
    public List<Student> m1() throws InterruptedException {

        return homeService.getStudent();
    }


    //with cache
    @GetMapping("/getStudentData")
    public List<Student> getStudentIndo() throws InterruptedException {

        return homeService.getStudentData();
    }

    //add student
    @PostMapping("/addStudent")
    public Student addStudentData(@RequestBody Student s)
    {
        return homeService.addStudent(s);
    }
}
