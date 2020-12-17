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

  purchases: Purchase[] = [];
  setpurchases: Purchase[] = [];
  condition: boolean = false;

  public activeUsername: string = sessionStorage.getItem('activeUsername')
  public user: User = new User(0,this.activeUsername,'','','','')

  ngOnInit(): void {
    this.getUserStocks();
  }

  getUserStocks(): void {
    this.stocksService.getUserStocks(this.user)
    .subscribe(purchases => {
      this.purchases = purchases;
      this.condition = this.checkCondition(purchases);
      this.setpurchases = this.createSetPurchases(purchases);
      console.log(this.purchases);
      console.log(this.setpurchases);
    });
  }

  checkCondition(purchases:Purchase[]): boolean {
    if(purchases === undefined || purchases.length == 0){
      return true;
    }else{
      return false;
    }
  }

  createSetPurchases(purchases:Purchase[]): Purchase[] {
    let setPurchases: Purchase[] = [];

    for(let x=0;x<purchases.length;x++){
      if(x==0){
        let num = purchases[0].price*purchases[0].amount
        purchases[0].price = parseInt(Math.round(num*100/100).toFixed(2),10)
        setPurchases.push(purchases[0]);
        continue;
      }
      for(let y=0;y<setPurchases.length;y++){
        if(purchases[x].stockString==setPurchases[y].stockString){
          setPurchases[y].price += purchases[x].price * purchases[x].amount;
          setPurchases[y].amount += purchases[x].amount;
          break;
        }else if(y==setPurchases.length-1){
          purchases[x].price=purchases[x].price*purchases[x].amount
          setPurchases.push(purchases[x]);
          break;
        }else{
        }
      }
    }
    return setPurchases;
  }

}
