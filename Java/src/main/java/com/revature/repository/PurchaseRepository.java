package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Purchase;

@Repository("purchaseRepository")

@Transactional
public class PurchaseRepository {

	private static Logger log = Logger.getLogger(PurchaseRepository.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public boolean save(Purchase purchase) {
		try {
			sessionFactory.getCurrentSession().save(purchase);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public List<Purchase> findPurchasesByUsername(String username) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Purchase.class)
			.createAlias("user", "u")
			.add(Restrictions.like("u.username", username)) // this should be the PROPERTY name of the class
			.list();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Purchase> findPurchasesBySymbol(String symbol) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Purchase.class)
			.createAlias("stock", "s")
			.add(Restrictions.like("s.stockSymbol", symbol)) // this should be the PROPERTY name of the class
			.list();
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<Purchase> findPurchasesByUsernameBySymbol(String username, String symbol) {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Purchase.class)
			.createAlias("user", "u").createAlias("stock", "s")
			.add(Restrictions.like("u.username", username))
			.add(Restrictions.like("s.stockSymbol", symbol))
			.list();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Purchase findPurchaseById(int id) {
		try {
			return (Purchase) sessionFactory.getCurrentSession().createCriteria(Purchase.class)
			.createAlias("user", "u").createAlias("stock", "s")
			.add(Restrictions.idEq(id))
			.list().get(0);
		} catch (Exception e) {
			return null;
		}
	}
	
	public boolean updatePurchase(Purchase purchase) {
		try {
			sessionFactory.getCurrentSession().update(purchase);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean deletePurchase(Purchase purchase) {
		Purchase activePurchase = findPurchaseById(purchase.getPurchaseid());
		try {
			if (activePurchase != null) {
				sessionFactory.getCurrentSession().delete(activePurchase);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
	public List<Purchase> findAll() {
		try {
			return sessionFactory.getCurrentSession().createCriteria(Purchase.class).list();
		} catch (Exception e) {
			return null;
		}
	}
}
