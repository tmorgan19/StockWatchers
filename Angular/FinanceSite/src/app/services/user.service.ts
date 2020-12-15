import { ClientMessage } from './../models/client-message.model';
import { SERVER_URL } from './../../environments/environment.prod';
import { Observable, of } from 'rxjs';
import { User } from './../models/user.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  }
  public currUser: Observable<User>

  constructor(private http: HttpClient) { }

  //Returns a ClientMessage indicating success or failure
  loginUser(user: User): Observable<ClientMessage> {
    return this.http.post<ClientMessage>(`${SERVER_URL}/login`, user).pipe(
      catchError(this.handleError<ClientMessage>('loginUser', null))
    )

  }

  //Returns a ClientMessage indicating success or failure
  registerUser(user: User): Observable<ClientMessage> {
    return this.http.post<ClientMessage>(`${SERVER_URL}/register`, user, this.httpOptions).pipe(
      catchError(this.handleError<ClientMessage>('registerUser', null))
    )
  }

  //Returns User from backend
  getUserByUsername(user: User): Observable<User> {
    return this.currUser = this.http.post<User>(`${SERVER_URL}/getUserByName`, user).pipe(
      catchError(this.handleError<User>('getUserByUsername', null))
    )
  }
  //Returns array of Users from backend
  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${SERVER_URL}/getUsers`).pipe(
      catchError(this.handleError<User[]>('getUserByUsername', null))
    )
  }
  
  //Returns a ClientMessage indicating success or failure
  updateUser(user: User): Observable<ClientMessage> {
    return this.http.post<ClientMessage>(`${SERVER_URL}/update`, user).pipe(
      catchError(this.handleError<ClientMessage>('updateUser', null))
    )
  }

  //Needs to be updated -- logout will not go to backend
  logoutUser(user: User): Observable<String> {
    return this.http.post<String>(`${SERVER_URL}/logout`, user).pipe(
      catchError(this.handleError<String>('logoutUser', null))
    )
  }

  //Error handling
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);

      return of(result as T);
    }
  }
}
