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
  condition: boolean;

  public activeUsername: string = sessionStorage.getItem('activeUsername')
  public user: User = new User(0,this.activeUsername,'','','','')

  ngOnInit(): void {
    this.getUserStocks();
    this.checkCondition(this.purchases);
  }

  getUserStocks(): void {
    this.stocksService.getUserStocks(this.user)
    .subscribe(purchases => this.purchases = purchases);
  }

  checkCondition(purchases){
    if(purchases==[]){
      this.condition == true;
    }else{
      this.condition == false;
    }
  }

}
