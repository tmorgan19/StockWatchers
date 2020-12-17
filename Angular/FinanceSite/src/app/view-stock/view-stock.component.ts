import { Purchase } from './../models/purchase.model';
import { ClientMessage } from './../models/client-message.model';
import { User } from './../models/user.model';
import { NavComponent } from './../nav/nav.component';
import { Observable, VirtualTimeScheduler } from 'rxjs';
import { StockSearch } from './../models/stock-search.model';
import { StocksService } from './../services/stocks.service';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-view-stock',
  templateUrl: './view-stock.component.html',
  styleUrls: ['./view-stock.component.css']
})
export class ViewStockComponent implements OnInit {

  constructor(private userService: UserService, private stockService: StocksService, private route: ActivatedRoute) { }

  public id: string;
  stock: StockSearch
  purchasedAmount = 0;
  value: string = '0';
  message: ClientMessage = new ClientMessage("");
  averageCost: string = "0"
  ngOnInit(): void {
    this.getStock()

  }

  getStock() {
    this.id = this.route.snapshot.paramMap.get('id')
    this.stockService.findStock(this.id).subscribe(
      data => {
        this.stock = data
        let u: User = new User(1, sessionStorage.getItem("activeUsername"), "123", "j", "enla", "enla@gmaiil.com")

        let q: Purchase[]
        let t: number = 0;
        let avg: number = 0;
        this.purchasedAmount = 0;
        this.stockService.getUserStocks(u).subscribe(
          data => {
            q = data,
              q.forEach(element => {
                if (element.stockString === this.stock.symbol) {
                  this.purchasedAmount += element.amount
                  avg += element.price
                  t += 1
                }

              });
            //Total value of stock
            this.value = (this.purchasedAmount * this.stock.latestPrice).toFixed(2)
            console.log(avg)
            console.log(t)
            //Average cost of each stock
            if(t >0)
            {
              this.averageCost = (avg / t).toFixed(2)

            }
            else{
              this.averageCost= "0"
            }
          }
        )



      }
    )
  }

  submitPurchase(value: number) {
    console.log(this.stock)
    this.message.message = "";
    let temp = this.stock;
    let u: User = new User(1, sessionStorage.getItem("activeUsername"), "123", "j", "enla", "enla@gmaiil.com")
    u.id = 1;
    if (value > 0) {

      this.stockService.saveUserStocks(u, temp, value).subscribe(
        data => {
          this.message = data;
          console.log(this.message)
          this.getStock();
        }
      );
    }
  }
  submitSell(value:number)
  {
    console.log(this.stock)
    this.message.message = "";

    let temp = this.stock;
    let u: User = new User(1, sessionStorage.getItem("activeUsername"), "123", "j", "enla", "enla@gmaiil.com")
    u.id = 1;
    if (value > 0) {

      this.stockService.sellUserStocks(u, temp, value).subscribe(
        data => {
          this.message = data;
          console.log(this.message)
          this.getStock();

        }
      );
    }
  }
  

}
