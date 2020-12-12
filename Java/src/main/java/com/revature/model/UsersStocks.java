package com.revature.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.postgresql.util.PGTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//TODO: convert users_stocks to purchases
@Entity
@Table(name="users_stocks")
public class UsersStocks {
	
	@Id
	@Column(name="purchaseid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int purchaseid;
	
	//the ignore property may not be nessecary but I had problems with
	//this in the previous project and this fixed it so I am putting them here
	//just in case
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="userid", nullable=false)
	private User user;

	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="stockid", nullable=false)
	private Stock stock;
	
	@Column(name="amount", nullable=false)
	private double amount; //todo this should be an int
	
	@Column(name="price", nullable=false)
	private double price;
	
	@Column(name="date", nullable=false)
	private PGTimestamp date;
	
	public UsersStocks() {}
	public UsersStocks(int purchaseid, User user, Stock stock, double amount, double price, PGTimestamp date) {
		this.purchaseid = purchaseid;
		this.user = user;
		this.stock = stock;
		this.amount = amount;
		this.price = price;
		this.date = date;
	}
	public UsersStocks(User user, Stock stock, double amount, double price, PGTimestamp date) {
		this.user = user;
		this.stock = stock;
		this.amount = amount;
		this.price = price;
		this.date = date;
	}
	
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	public Stock getStock() {
		return stock;
	}
	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public PGTimestamp getDate() {
		return date;
	}
	public void setDate(PGTimestamp date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "UsersStocks [user=" + user + ", stock=" + stock + ", amount=" + amount 
				+ ", price=" + price + ", date=" + date + "]";
	}
}