package com.revature.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.postgresql.util.PGTimestamp;

//@Entity
//@Table(name="users_stocks")
public class UsersStocks {

//	private User user;

	private Stock stock;
	
	private double purchase_amount;
	
	private PGTimestamp purchase_date;

//	public User getUser() {
//		return user;
//	}
//	public void setUser(User user) {
//		this.user = user;
//	}

	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getPurchase_amount() {
		return purchase_amount;
	}
	public void setPurchase_amount(double purchase_amount) {
		this.purchase_amount = purchase_amount;
	}

	public PGTimestamp getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(PGTimestamp purchase_date) {
		this.purchase_date = purchase_date;
	}

	@Override
	public String toString() {
		return "UsersStocks [user="/* + user */+ ", stock=" + stock + ", purchase_amount=" + purchase_amount + ", purchase_date="
				+ purchase_date + "]";
	}
}