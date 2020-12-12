package com.revature.rest;

import java.util.List;

import static com.revature.util.ClientMessageUtil.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
import com.revature.model.Stock;
import com.revature.model.User;
import com.revature.model.UsersStocks;
import com.revature.service.PurchaseService;

public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	//Add the purchase to the database
	@PostMapping("/newPurchase")
	public @ResponseBody ClientMessage newPurchase(@RequestBody UsersStocks purchase) {
		return (purchaseService.addPurchase(purchase)) ? PURCHASE_SUCCESSFUL : PURCHASE_UNSUCCESSFUL;
	}
	
	//Get purchases from the user provided
	@PostMapping("/getPurchaseByUser")
	public @ResponseBody List<UsersStocks> getPurchasesByUser(@RequestBody User user) {
		return purchaseService.getPurchasesByUsername(user.getUsername());
	}
	
	//Get purchases of the stock provided
	@PostMapping("/getPurchaseBySymbol")
	public @ResponseBody List<UsersStocks> getPurchasesByUser(@RequestBody Stock stock) {
		return purchaseService.getPurchasesByUsername(stock.getStockSymbol());
	}

	//Get all purchases
	@GetMapping("/getAllPurchases")
	public @ResponseBody List<UsersStocks> findAllUsersStockses() {
		return purchaseService.getAllPurchases();
	}
}
