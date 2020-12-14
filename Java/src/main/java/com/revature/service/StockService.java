package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Stock;
import com.revature.repository.StockRepository;

@Service("stockService")
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public Stock addStock(Stock stock) {
		Stock tempStock = stockRepository.findBySymbol(stock.getStockSymbol());
		if (tempStock == null) {
			stockRepository.save(stock);
			return null;
		}
		else return tempStock;
	}
}
