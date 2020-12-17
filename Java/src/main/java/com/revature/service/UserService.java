package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.User;
import com.revature.repository.UserRepository;

@Service("userService")
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public boolean registerUser(User user) {
		if (user.getUsername() == null || user.getPassword() == null) {
			return false;
		}
		else {
			if (userRepository.findByUsername(user.getUsername()) != null) {
				return false;
			} else {
				userRepository.save(user);
				return true;
			}
		}
	}
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}
	
	public User getUserById(int id) {
		return userRepository.findById(id);
	}
	
	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public boolean attemptLogin(User user) {
		boolean success = false;
		String username = user.getUsername();
		String password = user.getPassword();
		
		User userinDB = getUserByUsername(username);
		if (userinDB != null) {
			if (username.equalsIgnoreCase(userinDB.getUsername())) {
				if (password.equals(userinDB.getPassword())) {
					success = true;
				}
			}
		}
		return success;
	}

	public boolean updateInfo(User user) {
		User userinDB = getUserByUsername(user.getUsername());
		
		if (userinDB != null) {
			userinDB.setFirstName(user.getFirstName());
			userinDB.setLastName(user.getLastName());
			userinDB.setEmail(user.getEmail());
			userinDB.setPassword(user.getPassword());
			// updating userid and username not included
			userRepository.update(userinDB);
			return true;
		}
		else {
			return false;
		}
		
		
		
	}
}
