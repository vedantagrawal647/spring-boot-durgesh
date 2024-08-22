package com.aspectAOP.aopApplication.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public void makePayment(){
        System.out.println("Payment Started");
        //write logic of payment here
        System.out.println("payment ended");
    }

    public void someThing(){
        System.out.println("Something");
        int a=5;
        try{
            int res=5/0;
            System.out.println(res);
        }
        catch (Exception e)
        {
            throw new ArithmeticException();
        }

    }


}

