package com.springanotation.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import mypack.Dog;

import java.util.Date;

@SpringBootApplication
public class AnnotationApplication implements CommandLineRunner {

	@Autowired
	private Student student;

	@Autowired
	private Date date;

	@Autowired
	private Emp emp;

	@Autowired
	private Dog dog;

	@Autowired
	@Qualifier("User2")
	private User user;

	public static void main(String[] args) {
		SpringApplication.run(AnnotationApplication.class, args);
	}



	@Override
	public void run(String... args) throws Exception {
			student.studying();
			this.emp.whatsYourName();
			this.dog.eating();
			this.user.justPrint();


	}
}
