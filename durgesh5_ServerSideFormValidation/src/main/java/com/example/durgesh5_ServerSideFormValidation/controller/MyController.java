package com.example.durgesh5_ServerSideFormValidation.controller;

import com.example.durgesh5_ServerSideFormValidation.entities.LoginData;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;
import javax.validation.Valid;

@Controller
public class MyController {

    @GetMapping("/form")
    public String openForm(Model m)
    {
        m.addAttribute("loginData",new LoginData());
        return "form";
    }

    //handler for processing Form
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute("loginData") LoginData loginData, BindingResult result){
        if(result.hasErrors())
        {
            System.out.println(result);
            return "form";
        }
        System.out.println(loginData);
        return "success";
    }

    //now add
    //bean validation dependency
    //hibernate validation dependency
}
