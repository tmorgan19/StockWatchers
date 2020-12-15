package com.revature;

import org.apache.log4j.Logger;
import org.postgresql.Driver;
import org.postgresql.util.PGTimestamp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.dto.PurchaseDTO;
import com.revature.model.Purchase;
import com.revature.model.Stock;
import com.revature.model.User;
import com.revature.service.PurchaseService;
import com.revature.service.UserService;

public class Main {

	static Logger log = Logger.getLogger(Main.class);
	
	public static void main(String[] args) {

		// does not work after setting up controller
//		testUserHibernate();
		
		Purchase purchase = new Purchase(new User(99, "Test", "Test", "Test", "Test", "Test"), new Stock(99, "Test"), 20, 10.50, new PGTimestamp(System.currentTimeMillis()));
		PurchaseService purchaseService = new PurchaseService();
		PurchaseDTO purchaseDTO = purchaseService.convertToDTO(purchase);
		Purchase purchase2 = purchaseService.convertFromDTO(purchaseDTO);
		purchase2.setUser(purchase.getUser());
		purchase2.setStock(purchase.getStock());
		System.out.println("Starter form: " + purchase);
		System.out.println("DTO form: " + purchaseDTO);
		System.out.println("Converted back to normal form: " + purchase2);
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
