package com.security.springsecurityapplication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public  String homeEndPoint()
    {
        return "user";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public  String adminEndPoint()
    {
        return "admin";
    }
}
