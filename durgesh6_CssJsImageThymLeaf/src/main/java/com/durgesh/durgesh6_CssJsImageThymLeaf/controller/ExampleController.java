package com.durgesh.durgesh6_CssJsImageThymLeaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExampleController {

    @RequestMapping("/example")
    public String example(){
        return "example";
        //example.html
    }
}
