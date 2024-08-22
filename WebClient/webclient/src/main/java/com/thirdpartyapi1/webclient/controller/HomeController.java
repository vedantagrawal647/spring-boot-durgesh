package com.thirdpartyapi1.webclient.controller;

import com.thirdpartyapi1.webclient.entity.Employee;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/home1")
    public String m1()
    {
        return "Home1";
    }

    @GetMapping("/home2")
    public String m2()
    {
        return "Home2";
    }

    @GetMapping("/home3")
    public String m3()
    {
        return "Home3";
    }

    @PostMapping("/postData")
    public String m4(@RequestParam("username") String username,
                     @RequestParam("password") String password) {
        // Process username and password as needed
        System.out.println("Received username: " + username);
        System.out.println("Received password: " + password);

        return "Response from /postData"; // Return whatever response you want
    }

    @PostMapping("/postData2")
    public String m5(@RequestBody Employee employee) {

        System.out.println(employee);
        return "Response from /postData2"; // Return whatever response you want
    }
}
