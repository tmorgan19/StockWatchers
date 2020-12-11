package com.revature.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="stocks")
public class Stock {

	@Id
	@Column(name="stockid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockid;
	
	@Column(name="stock_symbol", unique=true, nullable=false)
	private String stockSymbol;
	
	//the ignore property may not be nessecary but I had problems with
	//this in the previous project and this fixed it so I am putting them here
	//just in case
	@JsonIgnore
	@OneToMany(mappedBy="stock")
	private List<UsersStocks> purchases;

	public Stock() {}
	public Stock(int stockid, String stockSymbol) {
		this.stockid = stockid;
		this.stockSymbol = stockSymbol;
	}
	public Stock(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getStockid() {
		return stockid;
	}

	public void setStockid(int stockid) {
		this.stockid = stockid;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	
	public List<UsersStocks> getPurchases() {
		return purchases;
	}
	
	@Override
	public String toString() {
		return "Stock [stockid=" + stockid + ", stockSymbol=" + stockSymbol + "]";
	}
}
