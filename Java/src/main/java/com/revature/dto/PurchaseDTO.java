package com.revature.dto;

import com.revature.model.Purchase;

public class PurchaseDTO {
	
	private int purchaseid;
	
	private int userId;

	private String stockString;
	
	private int amount;
	
	private double price;
	
	private String dateString;
	
	public PurchaseDTO() {}
	public PurchaseDTO(int purchaseid, int userId, String stockString, int amount, double price, String dateString) {
		this.purchaseid = purchaseid;
		this.userId = userId;
		this.stockString = stockString;
		this.amount = amount;
		this.price = price;
		this.dateString = dateString;
	}
	public PurchaseDTO(int userId, String stockString, int amount, double price, String dateString) {
		this.userId = userId;
		this.stockString = stockString;
		this.amount = amount;
		this.price = price;
		this.dateString = dateString;
	}
	public PurchaseDTO(Purchase purchase) {
		this(purchase.getPurchaseid(), purchase.getUser().getId(), 
				purchase.getStock().getStockSymbol(), purchase.getAmount(), 
				purchase.getPrice(), purchase.getDate().toString());
	}
	
	public int getPurchaseid() {
		return purchaseid;
	}
	public void setPurchaseid(int purchaseid) {
		this.purchaseid = purchaseid;
	}

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
		return "PurchaseDTO [purchaseid=" + purchaseid + ", userId=" + userId + ", stockString=" + stockString
				+ ", amount=" + amount + ", price=" + price + ", dateString=" + dateString + "]";
	}
}