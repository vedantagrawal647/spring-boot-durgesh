package com.security.custom_Security_Application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomePageController {

    @GetMapping("/signin")
    public String m1()
    {
        return "login";
    }

    @GetMapping("/invalid")
    public  String m2()
    {
        return "error";
    }


}
