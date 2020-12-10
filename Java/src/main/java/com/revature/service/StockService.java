package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Stock;
import com.revature.repository.StockRepository;

@Service("stockService")
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public boolean addStock(Stock stock) {
		if (stockRepository.findBySymbol(stock.getStockSymbol()) == null) return stockRepository.save(stock);
		else return false;
	}
}
