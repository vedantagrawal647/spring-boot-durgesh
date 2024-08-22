package com.durgesh.durgeag13_Lombok.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UContoller {

    @GetMapping("/signin")
    public String m1( ){
        return "login";
    }
}
