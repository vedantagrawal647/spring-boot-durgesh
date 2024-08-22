package com.aspectAOP.aopApplication.aspect;


import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MyAspect {


    //run before --  method makePayment() execution present in class PaymentService
    @Before("execution(* com.aspectAOP.aopApplication.service.PaymentService.makePayment())")
    public void printBefore()
    {
        System.out.println("before payment");
    }

    //run after --  method makePayment() execution present in class PaymentService
    @After("execution(* com.aspectAOP.aopApplication.service.PaymentService.makePayment())")
    public void printAfter()
    {
        System.out.println("after payment");
    }

    //run after --  method makePayment() successfull returning only
    @AfterReturning("execution(* com.aspectAOP.aopApplication.service.PaymentService.makePayment())")
    public void afterSuccesfullPayment()
    {
        System.out.println("after sucessfull returning");
    }

    //run after --  method someThing()  excution present in PaymentClass
    @After("execution(* com.aspectAOP.aopApplication.service.PaymentService.someThing())")
    public void printAfter2()
    {
        System.out.println("print after2");
    }

    //run after --  method someThing()  successfull returning only
    @AfterReturning("execution(* com.aspectAOP.aopApplication.service.PaymentService.someThing())")
    public void afterSuccesfullReturning()
    {
        System.out.println("after sucessfull returning");
    }

    //run after -- method someThing() successfull returning only
    @AfterThrowing("execution(* com.aspectAOP.aopApplication.service.PaymentService.someThing(..))")
    public void afterThrowingError()
    {
        System.out.println("throwing error");
    }

}