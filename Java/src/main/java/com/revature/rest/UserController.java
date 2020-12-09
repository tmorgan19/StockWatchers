package com.revature.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.User;
import com.revature.service.UserService;

@Controller("userController")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * Sending a POST request to http://localhost:8080/FinanceSite/register allows a user to be registered in DB
	 */
	@PostMapping("/register")
	public @ResponseBody String registerUser(@RequestBody User user) {
		return (userService.registerUser(user)) ? "It worked" : "It did not work";
	}
	
}
