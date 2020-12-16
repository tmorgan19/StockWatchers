package com.revature.dto;

import com.revature.model.Purchase;

public class PurchaseDTO {
	
	private int purchaseid;
	
	private String userString; //username

	private String stockString; //stockSymbol
	
	private int amount;
	
	private double price;
	
	private String dateString; //timestamp represented as string
	
	public PurchaseDTO() {}
	public PurchaseDTO(int purchaseid, String userString, String stockString, int amount, double price, String dateString) {
		this.purchaseid = purchaseid;
		this.userString = userString;
		this.stockString = stockString;
		this.amount = amount;
		this.price = price;
		this.dateString = dateString;
	}
	public PurchaseDTO(String userString, String stockString, int amount, double price, String dateString) {
		this.userString = userString;
		this.stockString = stockString;
		this.amount = amount;
		this.price = price;
		this.dateString = dateString;
	}
	public PurchaseDTO(Purchase purchase) {
		this(purchase.getPurchaseid(), purchase.getUser().getUsername(), 
				purchase.getStock().getStockSymbol(), purchase.getAmount(), 
				purchase.getPrice(), purchase.getDate().toString());
	}
	
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public String getUserString() {
		return userString;
	}
	public void setUserString(String userString) {
		this.userString = userString;
	}

	public String getStockString() {
		return stockString;
	}
	public void setStockString(String stockString) {
		this.stockString = stockString;
	}

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	
	@Override
	public String toString() {
		return "PurchaseDTO [purchaseid=" + purchaseid + ", userString=" + userString + ", stockString=" + stockString
				+ ", amount=" + amount + ", price=" + price + ", dateString=" + dateString + "]";
	}
}