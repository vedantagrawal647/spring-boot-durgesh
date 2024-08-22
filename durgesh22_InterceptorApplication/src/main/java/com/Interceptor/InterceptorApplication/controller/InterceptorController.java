package com.Interceptor.InterceptorApplication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptorController {

    @GetMapping("/home")
    public String handlingInterceptor1(){
        System.out.println("Custom Interceptor1");
        return "Home";
    }

    @GetMapping("/welcome")
    public String handlingInterceptor2(){
        System.out.println("Custom Interceptor2");
        return "Welcome";
    }
}
