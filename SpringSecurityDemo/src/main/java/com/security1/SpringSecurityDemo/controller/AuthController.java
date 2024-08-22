package com.security1.SpringSecurityDemo.controller;

import com.security1.SpringSecurityDemo.jwt_security.JwtHelper;
import com.security1.SpringSecurityDemo.model.JwtRequest;
import com.security1.SpringSecurityDemo.model.JwtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtHelper helper;

    public AuthController ( JwtHelper jwtHelper)
    {
        this.helper=jwtHelper;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping("/generate-token")
    public ResponseEntity<JwtResponse> login(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = userDetailsService.loadUserByUsername(authentication.getName());

        String token = this.helper.generateToken(userDetails);

        System.out.println("Generate JWT token  " + token);

        model.addAttribute("token", token);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
