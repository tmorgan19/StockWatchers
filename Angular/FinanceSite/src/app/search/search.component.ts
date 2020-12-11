import { StockSearch } from './../models/stock-search.model';
import { StockAll } from './../models/stock-all.model';
import { StocksService } from './../services/stocks.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private stockService: StocksService) { }

  stocks:StockAll[]
  currStock:StockSearch
  ngOnInit(): void {
    //this.getTopStocks();
    this.getStock();
  }

  getTopStocks()
  {
    this.stockService.getTopStocks().subscribe(
      data=> {this.stocks = data},
      err =>console.error(err),
      () => console.log('Done loading stocks')
    )
  }

  getStock()
  {
    this.stockService.findStock("tsla").subscribe(
      data => {this.currStock = data},
      err =>console.error(err),
      () => console.log('Done finding stocks')
    )
  }

}
