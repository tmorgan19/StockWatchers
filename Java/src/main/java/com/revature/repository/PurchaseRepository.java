package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.model.UsersStocks;

public class PurchaseRepository {

	private static Logger log = Logger.getLogger(PurchaseRepository.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(UsersStocks purchase) {
		try {
			sessionFactory.getCurrentSession().save(purchase);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<UsersStocks> findPurchasesByUsername(String username) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(UsersStocks.class)
			.add(Restrictions.like("user.username", username)) // this should be the PROPERTY name of the class
			.list();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<UsersStocks> findPurchasesBySymbol(String symbol) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(UsersStocks.class)
			.add(Restrictions.like("stock.stockSymbol", symbol)) // this should be the PROPERTY name of the class
			.list();
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public boolean deletePurchase(UsersStocks purchase) {
		try {
			sessionFactory.getCurrentSession().delete(purchase);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<UsersStocks> findAll(){
		return sessionFactory.getCurrentSession().createCriteria(UsersStocks.class).list();
	}
}
