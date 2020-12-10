package com.revature.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.model.Stock;
import com.revature.service.StockService;

@Controller("stockController")
public class StockController {

	@Autowired
	private StockService stockService;
	
	/*
	 * @PostMapping("/register")
	public @ResponseBody String registerUser(@RequestBody User user) {
		return (userService.registerUser(user)) ? "It worked" : "It did not work";
	}
	 */
	@PostMapping("/addStock")
	public @ResponseBody String addStock(@RequestBody Stock stock) {
		return (stockService.addStock(stock)) ? "It worked" : "It did not work";
	}
}
