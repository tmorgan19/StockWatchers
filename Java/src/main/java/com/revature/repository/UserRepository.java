package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.User;

@Repository("userRepository")

@Transactional
public class UserRepository {

	private static Logger log = Logger.getLogger(UserRepository.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(User user) {
		sessionFactory.getCurrentSession().save(user);
	}
	
	public User findByUsername(String username) {
		try {
			return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
			.add(Restrictions.like("username", username)) // this should be the PROPERTY name of the class
			.list()
			.get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<User> findAll(){
		return sessionFactory.getCurrentSession().createCriteria(User.class).list();
	}
}
