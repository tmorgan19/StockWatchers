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
		User validUser = new User("uname", "user", "fname", "lname", "fl@mail.com");
		
		when(userRepository.findByUsername("uname")).thenReturn(validUser);
		Assert.assertTrue(userService.attemptLogin(validUser));
	}
	
	@Test
	public void testLoginInvalidUser() {
		User invalidUser = new User("wrong", "wrong", "fname", "lname", "fl@mail.com");
		when(userRepository.findByUsername("wrong")).thenReturn(null);
		Assert.assertFalse(userService.attemptLogin(invalidUser));
	}
}
