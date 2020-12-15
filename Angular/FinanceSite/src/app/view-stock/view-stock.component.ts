import { User } from './../models/user.model';
import { NavComponent } from './../nav/nav.component';
import { Observable } from 'rxjs';
import { StockSearch } from './../models/stock-search.model';
import { StocksService } from './../services/stocks.service';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-view-stock',
  templateUrl: './view-stock.component.html',
  styleUrls: ['./view-stock.component.css']
})
export class ViewStockComponent implements OnInit {

constructor(private userService:UserService, private stockService:StocksService,private route:ActivatedRoute) { }

public id:string;
  stock: StockSearch
  ngOnInit(): void {
    this.id =this.route.snapshot.paramMap.get('id')
   this.stockService.findStock(this.id).subscribe(
     data => {
       if(data)
       {
        console.log("data found")
       }
       console.log(data);
       this.stock=data;
     }
   )
    
  }

  getStock()
  {
    this.id =this.route.snapshot.paramMap.get('id')
    this.stockService.findStock(this.id).subscribe(
      data => this.stock
    )
  }

  submitPurchase(value:number)
  {
  console.log(this.stock)
  let temp = this.stock;
  let u:User = new User("frank","123","j","enla","enla@gmaiil.com")
u.id= 1;
    if(value >0)
    {
      this.stockService.saveUserStocks(u,temp,value);
    }
  }

}
