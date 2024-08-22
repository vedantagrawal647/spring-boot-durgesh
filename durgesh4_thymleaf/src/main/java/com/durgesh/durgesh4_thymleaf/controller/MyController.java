package com.durgesh.durgesh4_thymleaf.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
public class MyController {

    @RequestMapping(value="/about",method = RequestMethod.GET )
    public  String about(Model model){
        System.out.println("Inside about  handler ..");

        model.addAttribute("name","Vedant");
        model.addAttribute("currentDate",new Date().toLocaleString());

        return "about";
        //about.html
    }

    @GetMapping("/example-loop")
    public String iterateHandler(Model m){

        //send

        //create a list,set collection
        List<String> names = List.of("Ankit","Laxmi","Karan","John");
        m.addAttribute("names",names);

        return "iterate";
        //iterate.html
    }

    //handler for conditional statement
    //three-types of handler
    //1. Elvis operator
    //2. if unless
    //3. Switch Case

    @GetMapping("/condition")
    public String conditionHandler(Model m)
    {
        System.out.println("conditional handler executed");

        m.addAttribute("isActive",true);
        m.addAttribute("gender","F");

        List<Integer> list = List.of(1,2,3,4,5);
        m.addAttribute("mylist",list);

        return "condition";
        //condition.html
    }

    //handler for including fragment
    @GetMapping("/service")
    public String serviceHandler(Model m)
    {
        m.addAttribute("title","I like to eat samosa");
        m.addAttribute("subtitle", LocalDateTime.now());
        //processing logic
        return "service";
    }

    //for new about
    //for base template
    @GetMapping("/newabout")
    public String newAbout(){
        return "aboutnew";
        //aboutnew.html
    }

    //for contacnt
    //for base template
    @GetMapping("/contact")
    public String contact(){
        return "contact";
        //aboutnew.html
    }
}
