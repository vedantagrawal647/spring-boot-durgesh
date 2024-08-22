package com.springanotation.annotation;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


import java.util.Date;

@Configuration
@ComponentScan(basePackages = {"mypack"})
public class MyConfig {

    @Bean
    public Student getStudent(){
        System.out.println("Student Object creation");
        return new Student();
    }

    @Bean("User1")
    @Lazy
    public User getUser(){
        System.out.println("User1 Object creation");
        return new User("User1");
    }

    @Bean("User2")
    @Lazy
    public User creatUser(){
        System.out.println("User2 Object creation");
        return new User("User2");
    }

    @Bean
    public Date getDate(){
        System.out.println("date object create");
        return new Date();
    }

}
