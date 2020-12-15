package com.revature.rest;

import java.util.List;

import static com.revature.util.ClientMessageUtil.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
import com.revature.model.Stock;
import com.revature.model.User;
import com.revature.model.Purchase;
import com.revature.service.PurchaseService;
import com.revature.service.UserService;

@Controller("purchaseController")
@CrossOrigin(origins = "http://localhost:4200")
public class PurchaseController {
	
	@Autowired
	private PurchaseService purchaseService;
	
	//Add the purchase to the database
	@PostMapping("/newPurchase")
	public @ResponseBody ClientMessage newPurchase(@RequestBody Purchase purchase) {
		return (purchaseService.addPurchase(purchase)) ? PURCHASE_SUCCESSFUL : PURCHASE_UNSUCCESSFUL;
	}
	
	//Get purchases from the user provided
	@PostMapping("/getPurchaseByUser")
	public @ResponseBody List<Purchase> getPurchasesByUser(@RequestBody User user) {
		return purchaseService.getPurchasesByUsername(user.getUsername());
	}
	
	//Get purchases of the stock provided
	@PostMapping("/getPurchaseBySymbol")
	public @ResponseBody List<Purchase> getPurchasesByUser(@RequestBody Stock stock) {
		return purchaseService.getPurchasesByUsername(stock.getStockSymbol());
	}

	//Get all purchases
	@GetMapping("/getAllPurchases")
	public @ResponseBody List<Purchase> findAllUsersStockses() {
		return purchaseService.getAllPurchases();
	}
}
