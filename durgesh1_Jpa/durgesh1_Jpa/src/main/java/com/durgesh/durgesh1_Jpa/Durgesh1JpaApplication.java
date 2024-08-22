package com.durgesh.durgesh1_Jpa;


import com.durgesh.durgesh1_Jpa.entites.UserEntity;
import org.apache.catalina.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@SpringBootApplication
public class Durgesh1JpaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(Durgesh1JpaApplication.class, args);


		UserRepo userRepo = context.getBean(UserRepo.class);

		//saving single user
//		UserEntity user1 = new UserEntity();
//		user1.setName("Divyanshi");
//		user1.setCity("mohali");
//		user1.setStatus("Y");
//		UserEntity user1s = userRepo.save(user1);
//		System.out.println(user1s);

//		UserEntity user2 = new UserEntity();
//		user2.setName("Pratyush");
//		user2.setCity("kanpur");
//		user2.setStatus("Y");
//		 userRepo.save(user2);
//
//		UserEntity user3 = new UserEntity();
//		user3.setName("divi");
//		user3.setCity("janua");
//		user3.setStatus("n");
//		userRepo.save(user3);
//
//		//saving multiple user
//		List<UserEntity> users = List.of(user2,user3);
//		Iterable<UserEntity> res = userRepo.saveAll(users);
//
//
//		//res.forEach(user -> System.out.println(user));

		//how to get data
		//1. findById(id) return optional contaning data
		//get data by id
		// Optional<UserEntity> optional = userRepo.findById(2);
		//System.out.println(optional);

		//2.
		//Iterable<UserEntity> itr = userRepo.findAll();
//		Iterator<UserEntity> iterator = itr.iterator();
//		while(iterator.hasNext())
//		{
//			UserEntity user=iterator.next();
//			System.out.println(user);
//		}

		//3.
//		Iterable<UserEntity> itr = userRepo.findAll();
//		itr.forEach(new Consumer<UserEntity>(){
//			@Override
//			public void accept(UserEntity t)
//			{
//				System.out.println(t);
//			}
//		});

		//4.
//		Iterable<UserEntity> itr = userRepo.findAll();
//		itr.forEach((user -> System.out.println(user)));



		//update data
//		Optional<UserEntity> optional = userRepo.findById(2);
//		UserEntity user = optional.get();
//		user.setName("Rahul");
//		UserEntity res= userRepo.save(user);
//		System.out.println(res);

		//Deleting the user entity



		//delete user of particular id
		//userRepo.deleteById(2);

		//delete all data
		//Iterable<UserEntity> allUsers =userRepo.findAll();
		//userRepo.deleteAll(allUsers);


		//drived query method ya custom finder method

		//List<UserEntity> users = userRepo.findByName("Vedant");
		//users.forEach(user -> System.out.println(user));

		//List<UserEntity> users = userRepo.findByNameAndCity("Vedant","mohali");
		//users.forEach(user -> System.out.println(user));

		//List<UserEntity> users = userRepo.findByNameOrCity("Vedant","kanpur");
		//users.forEach(user -> System.out.println(user));

//		List<UserEntity> users = userRepo.findByNameStartingWith("Ved");
//		users.forEach(user -> System.out.println(user));

//		List<UserEntity> users = userRepo.findByNameEndingWith("ivi");
//		users.forEach(user -> System.out.println(user));

//		List<UserEntity> users = userRepo.findByNameContaining("ivi");
//		users.forEach(user -> System.out.println(user));

//		List<UserEntity> user1 = userRepo.findByIdLessThan(53);
//		user1.forEach(user -> System.out.println(user));
//
//		System.out.println("----------------");
//
//		List<UserEntity> user2 = userRepo.findByIdGreaterThanEqual(53);
//		user2.forEach(user -> System.out.println(user));
//
//		System.out.println("----------------");
//
//		List<Integer> l=new ArrayList<>();
//		l.add(52);
//		l.add(54);
//		List<UserEntity> user3 = userRepo.findByIdIn(l);
//		user3.forEach(user -> System.out.println(user));
//
//		System.out.println("----------------");
//
//		List<UserEntity> user4 = userRepo.findByCityOrderByNameDesc("mohali");
//		user4.forEach(user -> System.out.println(user));

		// JPQL Query

//		List<UserEntity> user1 = userRepo.getAllUser();
//		user1.forEach(user -> System.out.println(user));

//		List<UserEntity> user2 = userRepo.getUserByName("Divyanshi");
//		user2.forEach(user -> System.out.println(user));

//		List<UserEntity> user3 = userRepo.getUserByNameAndCity("Divyanshi","mohali");
//		user3.forEach(user -> System.out.println(user));

		//Native Query

		List<UserEntity> user4 = userRepo.getUsersNative();
		user4.forEach(user -> System.out.println(user));




	}

}
