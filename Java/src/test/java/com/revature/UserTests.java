package com.revature;

import static org.mockito.Mockito.when;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.User;
import com.revature.repository.UserRepository;
import com.revature.service.UserService;

public class UserTests {

	User validUser = new User("uname", "user", "fname", "lname", "fl@mail.com");
	User invalidUser = new User("wrong", "wrong", "fname", "lname", "fl@mail.com");
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testLoginValidUser() {
		when(userRepository.findByUsername("uname")).thenReturn(validUser);
		Assert.assertTrue(userService.attemptLogin(validUser));
	}
	
	@Test
	public void testLoginInvalidUser() {
		when(userRepository.findByUsername("wrong")).thenReturn(null);
		Assert.assertFalse(userService.attemptLogin(invalidUser));
	}
	
	@Test
	public void testRegisterValidUser() {
		when(userRepository.findByUsername("uname")).thenReturn(null);
		Assert.assertTrue(userService.registerUser(validUser));
	}
	
	@Test
	public void testRegisterDuplicateUser() {
		when(userRepository.findByUsername("wrong")).thenReturn(invalidUser);
		Assert.assertFalse(userService.registerUser(invalidUser));
	}
	
	@Test
	public void testUpdateNonexistentUser() {
		when(userRepository.findByUsername("wrong")).thenReturn(null);
		Assert.assertFalse(userService.updateInfo(invalidUser));
	}
}
