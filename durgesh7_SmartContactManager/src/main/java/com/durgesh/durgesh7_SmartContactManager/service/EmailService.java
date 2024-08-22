package com.durgesh.durgesh7_SmartContactManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailService {



    @Autowired
    private JavaMailSender mailSender;



    @Value("$(spring.mail.username)")
    private String fromMail;


    public boolean sendMail(String tomail, String subject, String body) {
        try{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(fromMail);
            message.setTo(tomail);
            message.setText(body);
            message.setSubject(subject);

            mailSender.send(message);

            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error in sending otp");
            return false;
        }


    }
}
