package com.sessionmanagement.SessionUsingRedis.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String m1(HttpSession session)
    {
        return session.getId();
    }
}

