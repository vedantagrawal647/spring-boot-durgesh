package com.security.custom_Security_Application.controller;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

    @Autowired
    EmployeeRepository employeeRepository;

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","About - Smart Contact Manager");
        model.addAttribute("employee",new Employee());
        return "signup";
        //home.html
    }

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

    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("employee") Employee employee)
    {
            employee.setRoles("ROLE_"+employee.getRoles());
            employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
            Employee result = this.employeeRepository.save(employee);
            System.out.println(result);
            return "signup";
    }




}
