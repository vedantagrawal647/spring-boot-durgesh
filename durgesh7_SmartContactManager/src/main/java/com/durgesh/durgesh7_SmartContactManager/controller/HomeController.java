package com.durgesh.durgesh7_SmartContactManager.controller;

import com.durgesh.durgesh7_SmartContactManager.dao.UserDataRepository;
import com.durgesh.durgesh7_SmartContactManager.entities.Contact;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import com.durgesh.durgesh7_SmartContactManager.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDataRepository userDataRepository;


    @RequestMapping("/")
    public String home(Model model){
        model.addAttribute("title","Home - Smart Contact Manager");
        return "home";
        //home.html
    }
    @RequestMapping("/about")
    public String about(Model model){
        model.addAttribute("title","About - Smart Contact Manager");
        return "about";
        //home.html
    }

    @RequestMapping("/signup")
    public String signup(Model model){
        model.addAttribute("title","About - Smart Contact Manager");
        model.addAttribute("userData",new UserData());
        return "signup";
        //home.html
    }

    //handler for registering userData
    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("userData") UserData userData,
                               BindingResult result1,
                               @RequestParam(value="agreement",defaultValue = "false") boolean agreement,
                               Model model,
                               HttpSession session)
    {
       try{
           if(!agreement)
           {
               System.out.println("You have not aggred the terms and conditions");
               throw new Exception("You have not aggred the terms and conditions");
           }

           if(result1.hasErrors())
           {
               System.out.println("Error"+ result1.toString());
               model.addAttribute("userData",userData);
               return "signup";
           }

           userData.setRole("ROLE_USER");
           userData.setEnabled("true");
           userData.setImageUrl("default.png");
           userData.setPassword(passwordEncoder.encode(userData.getPassword()));

           UserData result = this.userDataRepository.save(userData);
           System.out.println(result);

           model.addAttribute("userData",new UserData());

           session.setAttribute("message",new Message("Success Registered !! ","alert-success "));
           return "signup";


       }
       catch (Exception e)
       {
            e.printStackTrace();
            model.addAttribute("userData",userData);
            session.setAttribute("message",new Message("Something went wrong !!" ,"alert-danger"));
           return "signup";
       }

    }

    //handler for custom login
    @GetMapping("/signin")
    public String customLogin(Model model)
    {

        model.addAttribute("title","Login page");

        return "login";
    }



}
