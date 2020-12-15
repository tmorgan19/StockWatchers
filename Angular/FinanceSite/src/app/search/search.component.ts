
import { finalize, map, tap } from 'rxjs/operators'
import { StockSearch } from './../models/stock-search.model';
import { StockAll } from './../models/stock-all.model';
import { StocksService } from './../services/stocks.service';
import { Component, OnInit } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { SlicePipe } from '@angular/common';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private stockService: StocksService) { }

  stocks: StockAll[]
  stock$: Observable<StockAll[]>;
  currStock:StockSearch
  searchPattern:string;

  private searchTerms = new Subject<string>();
  ngOnInit(): void {
    //this.getTopStocks();
    //this.getStock();
    
    this.stock$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term:string) => this.stockService.stockSearch(term).pipe(
        map(data => data.sort(this.sortByName)
      ))
     
    )
    )
    
  }
  sortByName(a,b) {
    if (a.symbol < b.symbol)
      return -1;
    if (a.symbol > b.symbol)
      return 1;
    return 0;
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

  searchStocks(term:string):void
  {
    this.searchTerms.next(term);
    this.searchPattern=term;
    console.log("new Term"+ term);

  }
  //Sorts list by letter in asc or desc order. Should really sort based on provided term to make finding a stock easier.
   sortListDir() {
     console.log("running sort")
    var list, i, switching, b, shouldSwitch, dir, switchcount = 0;
    list = document.getElementById("searchResult");
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    // Make a loop that will continue until no switching has been done:
    while (switching) {
      // Start by saying: no switching is done:
      switching = false;
      b = list.getElementsByTagName("LI");
      // Loop through all list-items:
      for (i = 0; i < (b.length - 1); i++) {
        // Start by saying there should be no switching:
        shouldSwitch = false;
        /* Check if the next item should switch place with the current item,
        based on the sorting direction (asc or desc): */
        if (dir == "asc") {
          if (b[i].innerHTML.toLowerCase() > b[i + 1].innerHTML.toLowerCase()) {
            /* If next item is alphabetically lower than current item,
            mark as a switch and break the loop: */
            shouldSwitch = true;
            break;
          }
        } else if (dir == "desc") {
          if (b[i].innerHTML.toLowerCase() < b[i + 1].innerHTML.toLowerCase()) {
            /* If next item is alphabetically higher than current item,
            mark as a switch and break the loop: */
            shouldSwitch= true;
            break;
          }
        }
      }
      if (shouldSwitch) {
        /* If a switch has been marked, make the switch
        and mark that a switch has been done: */
        b[i].parentNode.insertBefore(b[i + 1], b[i]);
        switching = true;
        // Each time a switch is done, increase switchcount by 1:
        switchcount ++;
      } else {
        /* If no switching has been done AND the direction is "asc",
        set the direction to "desc" and run the while loop again. */
        if (switchcount == 0 && dir == "asc") {
          dir = "desc";
          switching = true;
        }
      }
    }
  }

  
}
