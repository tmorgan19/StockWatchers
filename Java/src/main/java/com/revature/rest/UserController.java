package com.revature.rest;

import static com.revature.util.ClientMessageUtil.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
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
	public @ResponseBody ClientMessage registerUser(@RequestBody User user) {
		return (userService.registerUser(user)) ? REGISTRATION_SUCCESSFUL : REGISTRATION_UNSUCCESSFUL;
	}
	
	
	/**
	 * POST request to http://localhost:8080/FinanceSite/login where username and password are sent as JSON in body
	 * will check credentials and return if valid login or not
	 */
	@PostMapping("/login")
	public @ResponseBody ClientMessage attemptLogin(@RequestBody User user) {
		return (userService.attemptLogin(user)) ? LOGIN_SUCCESSFUL : LOGIN_UNSUCCESSFUL;
	}
	
	
	@PostMapping("/getUserByName")
	public @ResponseBody User getUserByName(@RequestBody User user) {
		return userService.getUserByUserame(user.getUsername());
	}
	
	@PostMapping("/getUsers")
	public @ResponseBody List<User> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping("/update")
	public @ResponseBody ClientMessage updateUserInfo(@RequestBody User user) {
		return (userService.updateInfo(user)) ? UPDATE_SUCCESSFUL : UPDATE_UNSUCCESSFUL;
	}
}
