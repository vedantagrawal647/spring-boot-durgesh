package com.aspectAOP.aopApplication;

import com.aspectAOP.aopApplication.service.PaymentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(AopApplication.class, args);

		PaymentService paymentObject = context.getBean(PaymentService.class);

		paymentObject.makePayment();

		System.out.println("***************");
		paymentObject.someThing();


	}

}
