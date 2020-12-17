package com.revature.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Stock;
import com.revature.model.User;
import com.revature.dto.PurchaseDTO;
import com.revature.model.Purchase;
import com.revature.repository.PurchaseRepository;
import com.revature.repository.StockRepository;
import com.revature.repository.UserRepository;

@Service("purchaseService")
public class PurchaseService {
	
	private static Logger log = Logger.getLogger(PurchaseService.class);
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StockService stockService;
	
	//add the stock to the stocks table (if it isnt there already)
	//then add the purchase
	public boolean addPurchase(Purchase purchase) {
		Stock tempStock = stockService.addStock(purchase.getStock());
		if (tempStock == null) return purchaseRepository.save(purchase);
		else {
			purchase.setStock(tempStock);
			return purchaseRepository.save(purchase);
		}
	}
	
	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}
	
	public List<Purchase> getPurchasesByUsername(String username) {
		return purchaseRepository.findPurchasesByUsername(username);
	}
	
	public List<Purchase> getPurchasesBySymbol(String symbol) {
		return purchaseRepository.findPurchasesBySymbol(symbol);
	}
	
	public boolean deletePurchase(Purchase purchase) {
		return purchaseRepository.deletePurchase(purchase);
	}
	
	public PurchaseDTO convertToDTO(Purchase purchase) {
		return new PurchaseDTO(purchase);
	}
	public List<PurchaseDTO> convertToDTO(List<Purchase> purchases) {
		List<PurchaseDTO> newPurchaseList = new ArrayList<PurchaseDTO>();
		for (int i = 0; i < purchases.size(); i++) newPurchaseList.add(new PurchaseDTO(purchases.get(i)));
		return newPurchaseList;
	}
	
	public Purchase convertFromDTO(PurchaseDTO purchaseDTO) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		java.util.Date parsedDate;
		try {
			parsedDate = dateFormat.parse(purchaseDTO.getDateString());
		} catch (ParseException e) {
			parsedDate = null;
			e.printStackTrace();
		}
		PGTimestamp tempTimestamp = new PGTimestamp(parsedDate.getTime());
		
		return new Purchase(purchaseDTO.getPurchaseid(), 
				userService.getUserByUsername(purchaseDTO.getUserString()), 
				stockService.addStock(purchaseDTO.getStockString()),
				purchaseDTO.getAmount(), 
				purchaseDTO.getPrice(), 
				tempTimestamp);
	}

	public boolean removeStockBySymbolByUser(PurchaseDTO purchase) {
		Purchase purchaseObj = convertFromDTO(purchase);
		
		// 1. take purchase and determine User, Stock, Amount
		int removalAmount = purchaseObj.getAmount();
		User removalUser = purchaseObj.getUser();
		Stock removalStock = purchaseObj.getStock();
		
		// 2. select purchases by user by stock and determine if the user can remove the amount (it wont result in a negative amount)
		List<Purchase> purchaseList = purchaseRepository.findPurchasesByUsernameBySymbol(removalUser.getUsername(), 
				removalStock.getStockSymbol());
		
		int sum = 0;
		for (int i = 0; i < purchaseList.size(); i++) sum += purchaseList.get(i).getAmount();
		if (removalAmount > sum) return false;
		
		// 3. step through purchases and remove each one until the given amount is removed 
		for (int i = 0; i < purchaseList.size(); i++) {			
			// if the amount in the purchase is less than the removalAmount, delete the purchase
			if (purchaseList.get(i).getAmount() <= removalAmount) {
				removalAmount -= purchaseList.get(i).getAmount();
				purchaseRepository.deletePurchase(purchaseList.get(i));
			} else { // if the amount remaining is greater than the removalAmount
				purchaseList.get(i).setAmount(purchaseList.get(i).getAmount()-removalAmount);
				purchaseRepository.updatePurchase(purchaseList.get(i));
				break;
			}
			
			if (removalAmount == 0) break;
		}
		
		return true;
	}
}