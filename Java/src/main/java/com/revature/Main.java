package com.revature;

import org.apache.log4j.Logger;
import org.postgresql.Driver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.model.User;
import com.revature.service.UserService;

public class Main {

	static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {

		testUserHibernate();
		
		
	}

	public static void testUserHibernate() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		UserService userService = ac.getBean("userService", UserService.class);
		
		User u1 = new User("example", "password", "John", "Smith", "jsmith@gmail.com");
		User u2 = new User("example2", "password2", "Jane", "Smithson", "janes@gmail.com");
		
		log.trace(userService.registerUser(u1) ? "Registered successfully" : "Couldn't register");
		log.trace(userService.registerUser(u2) ? "Registered successfully" : "Couldn't register");
		
		System.out.println("Find all: " + userService.getAllUsers());
		
		System.out.println("Find one: " + userService.getUserByUserame("example"));
	}

}
