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
			return stock;
		}
		else return tempStock;
	}
	public Stock addStock(String stockString) {
		Stock tempStock = stockRepository.findBySymbol(stockString);
		if (tempStock == null) {
			stockRepository.save(new Stock(stockString));
			return stockRepository.findBySymbol(stockString);
		}
		else return tempStock;
	}
	
	public Stock getStockBySymbol(String symbol) {
		return stockRepository.findBySymbol(symbol);
	}
}
