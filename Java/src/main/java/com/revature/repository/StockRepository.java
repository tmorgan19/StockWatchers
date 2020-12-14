package com.revature.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.model.Stock;

@Repository("stockRepository")

@Transactional
public class StockRepository {

	private static Logger log = Logger.getLogger(StockRepository.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Stock save(Stock stock) {
		try {
			sessionFactory.getCurrentSession().save(stock);
			return stock;
		} catch (Exception e) {
			return null;
		}
	}
	
	public Stock findById(int stockid) {
		try {
			return (Stock) sessionFactory.getCurrentSession().createCriteria(Stock.class)
					.add(Restrictions.idEq(stockid)).list().get(0);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public Stock findBySymbol(String symbol){
		try {
			return (Stock) sessionFactory.getCurrentSession().createCriteria(Stock.class)
			.add(Restrictions.like("stockSymbol", symbol)).list().get(0); //refers to model not db
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	public List<Stock> findAll(){
		return sessionFactory.getCurrentSession().createCriteria(Stock.class).list();
	}
}
