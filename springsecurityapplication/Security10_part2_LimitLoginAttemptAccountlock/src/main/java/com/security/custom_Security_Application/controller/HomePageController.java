package com.security.custom_Security_Application.controller;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
public class HomePageController {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

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



//    @GetMapping("/invalid")
//    public  String m2()
//    {
//        return "error";
//    }

    @PostMapping("/do_register")
    public String registerUser(@ModelAttribute("employee") Employee employee, HttpServletRequest request)
    {

            employee.setRoles("ROLE_"+employee.getRoles());
            String password =employee.getPassword();
            employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));

            employee.setEnable(false);
            employee.setVerificationCode(UUID.randomUUID().toString());
            employee.setAccountNonLocked(true);
            employee.setFailedAttempt(0);
            employee.setLockTime(null);



        Employee newuser = this.employeeRepository.save(employee);
        if(newuser!=null)
        {
            String to = employee.getEmail();

            String url = request.getRequestURL().toString();
            url=url.replace(request.getServletPath(),"");

            this.employeeService.sendMail(to, url,newuser);
        }

        return "signup";
    }

    @GetMapping("/verify")
    public String  verifyAccount(@Param("code") String code,Model m)
    {
        boolean f = employeeService.verificationAccount(code);

        if(f){
            m.addAttribute("msg","Successfully your account is verified");
        }
            else{
            m.addAttribute("msg","May we your verification code is incorrect or already verified");
            }
        return "message";
    }



}
