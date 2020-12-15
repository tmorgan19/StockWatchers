package com.revature.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.postgresql.util.PGTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Stock;
import com.revature.model.User;
import com.revature.dto.PurchaseDTO;
import com.revature.model.Purchase;
import com.revature.repository.PurchaseRepository;
import com.revature.repository.StockRepository;

@Service("purchaseService")
public class PurchaseService {
	
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
				userService.getUserByUserame(purchaseDTO.getUserString()), 
				stockService.getStockBySymbol(purchaseDTO.getStockString()),
				purchaseDTO.getAmount(), 
				purchaseDTO.getPrice(), 
				tempTimestamp);
	}
}