package com.durgesh.durgesh7_SmartContactManager.controller;

import com.durgesh.durgesh7_SmartContactManager.dao.UserDataRepository;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import com.durgesh.durgesh7_SmartContactManager.helper.Message;
import com.durgesh.durgesh7_SmartContactManager.service.EmailService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class ForgotController {

    @Autowired
     private EmailService emailService;

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private BCryptPasswordEncoder bcrypt;



    Random random = new Random(1000);

    //email id from open handler
    @GetMapping("/forgot")
    public String openEmailForm()
    {
        return "forgot_email_form";
    }

    @PostMapping("/send-otp")
    public String sendOTP(@RequestParam("email") String email, HttpSession session)
    {
        //generating otp of 4 digit

        int otp = random.nextInt(999999);

        // write code for sending otp to mail
        String subject = "Otp From SCM" ;
        String message =" OTP = " + otp ;
        String to = email;

        boolean flag = this.emailService.sendMail(to,subject,message);


        if(flag){
            System.out.println(otp);
            session.setAttribute("myotp",otp);
            session.setAttribute("email",email);

            return "verify_otp";
        }
        else {
            session.setAttribute("message",new Message("Otp Send Failed  Resend OTP !!","danger"));
            return "forgot_email_form";
        }
    }


    // verify otp handler
    @PostMapping("/verify-otp")
    public String verifyOtp(@RequestParam("otp") int otp,HttpSession session)
    {
        int myOtp = (int)session.getAttribute("myotp");
        String email = (String)session.getAttribute("email");

        if(myOtp == otp)
        {
            //password change form
            UserData userData = this.userDataRepository.getUserDataByUserDataName(email);
            System.out.println(userData);
            if(userData == null)
            {
                // send error message
                session.setAttribute("message",new Message("User does not exits with this email !!","danger"));
                return "redirect:/forgot";
            }
            else {
                //send change password form
            }
            return "password_change_form";
        }
        else {
            session.setAttribute("message",new Message("You have enter wrong OTP !!","danger"));
            return "redirect:/verify-otp";
        }
    }

    //change password handler
    @PostMapping("/change-password")
    public String changePassword(@RequestParam("newpassword") String newpassword,HttpSession session)
    {
        String email = (String)session.getAttribute("email");
        UserData userData = this.userDataRepository.getUserDataByUserDataName(email);
        userData.setPassword(bcrypt.encode(newpassword));
        this.userDataRepository.save(userData);
        return "redirect:/signin?change=password changed successfully..";
    }
}
