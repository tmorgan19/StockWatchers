package com.revature.rest;

import static com.revature.util.ClientMessageUtil.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.ajax.ClientMessage;
import com.revature.model.Stock;
import com.revature.service.StockService;

@Controller("stockController")
@CrossOrigin(origins = "*")
public class StockController {

	@Autowired
	private StockService stockService;
	
	/*
	 * First checks if the stock posted exists in the db, if not, adds it, otherwise returns false
	 */
	@PostMapping("/addStock")
	public @ResponseBody ClientMessage addStock(@RequestBody Stock stock) {
		return (stockService.addStock(stock) == null) ? STOCK_SAVED : STOCK_WAS_NOT_SAVED;
	}
}
