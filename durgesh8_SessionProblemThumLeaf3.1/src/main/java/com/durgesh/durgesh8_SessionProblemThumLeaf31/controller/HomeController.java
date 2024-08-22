package com.durgesh.durgesh8_SessionProblemThumLeaf31.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.SessionHelper;

@Controller
public class HomeController {

    @Autowired
    private SessionHelper sessionHelper;

    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        session.setAttribute("message", "Hello success");
        sessionHelper.removeMessageFromSession(session); // Call the method to remove message from session
        return "home";
    }

    @GetMapping("/about")
    public String about(HttpSession session) {
        session.setAttribute("message", "Hello success");
        return "about";
    }

    @GetMapping("/contact")
    public String contact(HttpSession session) {
        session.setAttribute("message", "Hello success");
        return "contact";
    }
}
