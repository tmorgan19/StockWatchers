package com.revature.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.UsersStocks;
import com.revature.repository.PurchaseRepository;
import com.revature.repository.StockRepository;

@Service("purchaseService")
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private StockRepository stocksRepository;
	
	//add the stock to the stocks table (if it isnt there already)
	//then add the purchase
	public boolean addPurchase(UsersStocks purchase) {
		stocksRepository.save(purchase.getStock());
		return purchaseRepository.save(purchase);
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