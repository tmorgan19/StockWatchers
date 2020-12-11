import { ClientMessage } from './../models/client-message.model';
import { SERVER_URL } from './../../environments/environment.prod';
import { Observable, of } from 'rxjs';
import { User } from './../models/user.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
public currUser:Observable<User>

  constructor(private http:HttpClient) { }

//Returns a string that either reads: "Log in successful" or "Log in unsuccessful"
loginUser(user:User): Observable<String>
{
  return this.http.post<String>(`${SERVER_URL}/login`,user).pipe(
    catchError(this.handleError<String>('loginUser',null))
  )

}

//Returns a string that either reads: "It worked" or "It did not work"

registerUser(user:User): Observable<String> {
  return this.http.post<String>(`${SERVER_URL}/register`,user).pipe(
    catchError(this.handleError<String>('registerUser',null))
  )
}

//Does not currently exist on the server, however it will need to.
getUserByUsername(username:String): Observable<User>
{
  return this.currUser = this.http.post<User>(`${SERVER_URL}/getUserByName`,username).pipe(
    catchError(this.handleError<User>('getUserByUsername',null))
  )
}

//Error handling
 private handleError<T>(operation = 'operation', result?:T)
  {
    return(error: any): Observable<T> =>{
      console.error(error);

      return of(result as T);
    }
  }
}
