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
import javax.persistence.Table;

@Entity
@Table(name="stocks")
public class Stock {

	@Id
	@Column(name="stockid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stockid;
	
	@Column(name="stock_symbol", unique=true, nullable=false)
	private String stockSymbol;

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
	
	@Override
	public String toString() {
		return "Stock [stockid=" + stockid + ", stockSymbol=" + stockSymbol + "]";
	}
}
