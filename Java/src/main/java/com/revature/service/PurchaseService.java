package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Stock;
import com.revature.model.UsersStocks;
import com.revature.repository.PurchaseRepository;
import com.revature.repository.StockRepository;

@Service("purchaseService")
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private StockService stockService;
	
	//add the stock to the stocks table (if it isnt there already)
	//then add the purchase
	public boolean addPurchase(UsersStocks purchase) {
		Stock tempStock = stockService.addStock(purchase.getStock());
		if (tempStock == null) return purchaseRepository.save(purchase);
		else {
			purchase.setStock(tempStock);
			return purchaseRepository.save(purchase);
		}
	}
	
	public List<UsersStocks> getAllPurchases() {
		return purchaseRepository.findAll();
	}
	
	public List<UsersStocks> getPurchasesByUsername(String username) {
		return purchaseRepository.findPurchasesByUsername(username);
	}
	
	public List<UsersStocks> getPurchasesBySymbol(String symbol) {
		return purchaseRepository.findPurchasesBySymbol(symbol);
	}
	
	public boolean deletePurchase(UsersStocks purchase) {
		return purchaseRepository.deletePurchase(purchase);
	}
}