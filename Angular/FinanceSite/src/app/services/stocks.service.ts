import { MessageService } from './message.service';
import {  StockSearch } from './../models/stock-search.model';
import { IEC_API_URL , IEC_API_KEY} from './../../environments/environment.prod';
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

  //Returns a list of the top stocks with everything but their monetary value. A user will have to search a specific stock to see that (or click on that stock)
  public getTopStocks(): Observable<StockAll[]>
  {
  
    return  this.http.get<StockAll[]>(`${IEC_API_URL}ref-data/symbols?token=${IEC_API_KEY}`).pipe(
      catchError(this.handleError<StockAll[]>('getTopStocks',[])))  
  }

  //Returns a stock based on the symbol. CANNOT DO COMPANY NAME
  public findStock(symbol:string): Observable<StockSearch>
  {
    return this.http.get<StockSearch>(`${IEC_API_URL}stock/${symbol}/quote?token=${IEC_API_KEY}`).pipe(
      catchError(this.handleError<StockSearch>('findStock',null))  
    )

  
  }
  //DOES NOT WORK RIGHT NOW
  public stockSearch(pattern:string):Observable<StockAll[]>
  {
    if(!pattern.trim())
    {
      return of([]);
    }
    return this.http.get<StockAll[]>(`api/stocksList/?name=${pattern}`).pipe(
      tap(x =>x.length ? this.log(`found stocks matching ${pattern}`) : this.log(`no stocks found matching ${pattern}`)), this.handleError<StockAll[]>('stockSearch',[])
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
