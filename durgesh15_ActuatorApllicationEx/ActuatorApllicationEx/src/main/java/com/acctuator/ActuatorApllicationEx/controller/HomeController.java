package com.acctuator.ActuatorApllicationEx.controller;

import com.acctuator.ActuatorApllicationEx.helper.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class HomeController {

    @Autowired
    private Student student;

    @GetMapping("/home")
    public Map<String,String> getData(){
        return Map.of("Name","Durgesh Tiwari");
    }
}
