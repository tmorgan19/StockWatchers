import { User } from './../models/user.model';
import { Purchase } from './../models/purchase.model';
import { StocksService } from './../services/stocks.service';
import { StockSearch } from './../models/stock-search.model';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private stocksService:StocksService) { }

  purchases: Purchase[];
  purchases04: Purchase[];
  user: User;

  ngOnInit(): void {
    this.getUserStocks();
  }

  getUserStocks(): void {
    this.stocksService.getUserStocks(this.user)
    .subscribe(purchases => this.purchases04 = purchases.slice(0, 4));
  }

}
