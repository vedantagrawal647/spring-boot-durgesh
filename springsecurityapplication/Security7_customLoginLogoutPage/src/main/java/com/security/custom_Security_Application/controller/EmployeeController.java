package com.security.custom_Security_Application.controller;


import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/ab")
    public void m1()
    {
        Employee employee1 = new Employee( 3,"Vedant","v@gmail.com",new BCryptPasswordEncoder().encode("pass1"),"ROLE_USER");
        Employee employee2 = new Employee( 4,"divyanshi","d@gmail.com",new BCryptPasswordEncoder().encode("pass2"),"ROLE_ADMIN");
        employeeService.addEmployee(employee1);
        employeeService.addEmployee(employee2);
    }


    @GetMapping("/home")
    public String home()
    {
        return "home";
    }

    @GetMapping("/about")
    public String about()
    {

        return "about";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

}
