import { ClientMessage } from './../models/client-message.model';
import { map } from 'rxjs/operators';
import { User } from './../models/user.model';
import { Purchase } from './../models/purchase.model';
import { MessageService } from './message.service';
import {  StockSearch } from './../models/stock-search.model';
import { IEC_API_URL, IEC_API_KEY, SERVER_URL } from './../../environments/environment.prod';
import { Stock } from './../models/stock.model';
import { StockAll } from './../models/stock-all.model';
import { Injectable } from '@angular/core';
import {HttpClient,HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import{catchError,tap} from 'rxjs/operators';
import { temporaryAllocator } from '@angular/compiler/src/render3/view/util';
import { query } from '@angular/animations';
@Injectable({
  providedIn: 'root'
})
export class StocksService {

 httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};
  constructor(private http: HttpClient, private messageService:MessageService) { }

  stockList:StockAll[]

  //Returns a list of the top stocks with everything but their monetary value. A user will have to search a specific stock to see that (or click on that stock)
  //Should be used to generate search list, as it doesn't have a api cost
  public getTopStocks(): Observable<StockAll[]>
  {
  
      return this.http.get<StockAll[]>(`${IEC_API_URL}tops?token=${IEC_API_KEY}`).pipe(
        catchError(this.handleError<StockAll[]>('getTopStocks',[]))
        )
  }

  //Returns a stock based on the symbol. CANNOT DO COMPANY NAME
  public findStock(symbol:string): Observable<StockSearch>
  {
    console.log("findingStock: " + symbol)
    return this.http.get<StockSearch>(`${IEC_API_URL}stock/${symbol}/quote?token=${IEC_API_KEY}`).pipe(
      catchError(this.handleError<StockSearch>('findStock',null)) 
    )

  
  }
  public stockSearch(pattern:string):Observable<StockAll[]>
  {
   console.log("searching for matching stocks")
    if(pattern ==="")
    {
      console.log("search was null")

      return of([])
    }
    let temp = this.getTopStocks();
    console.log(temp);
    return temp.pipe(map(temp => temp.filter(stock => stock.symbol.startsWith(pattern.toUpperCase()))
    ), catchError(this.handleError<any>('stockSearch',[]))
    )
  }
  
  public getUserStocks(user:User): Observable<Purchase[]>
  {
    return this.http.post<Purchase[]>(`${SERVER_URL}/getPurchaseByUser`,user).pipe(
      catchError(this.handleError<Purchase[]>('getUserStocks',[])
    ))
  }

  public saveUserStocks(user:User,stock:StockSearch,amount:number):Observable<ClientMessage>
  {
    console.log("User: " +user.username)
    console.log("Stock: " +stock.symbol)

    let p:Purchase = new Purchase();
    p.stockString = stock.symbol;
    p.userString = user.username
    p.amount = amount;
    p.purchaseid =0;
    p.price = stock.latestPrice;
    p.dateString = "2020-10-02 02:02:02.000"
    return this.http.post<ClientMessage>(`${SERVER_URL}/newPurchase`,p).pipe(
      catchError(this.handleError<ClientMessage>("saveUserStocks",null))
    )
  }


  private handleError<T>(operation = 'operation', result?:T)
  {
    return(error: any): Observable<T> =>{
      console.error(error);

      return of(result as T);
    }
  }

  private log(message:string)
  {
    this.messageService.add(`Stock Service: ${message}`);
  }

 



}
