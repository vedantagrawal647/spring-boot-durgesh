package com.springanotation.annotation.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionController {

    @GetMapping("/exception")
    public void m1()
    {
        throw new UserNotFoundException("user not found");
    }
}
