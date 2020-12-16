import { MessageService } from './message.service';
import { catchError, map } from 'rxjs/operators';
import { User } from './../models/user.model';
import { UserService } from './user.service';
import { HttpClient, HttpHeaders,  } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';

@Injectable({
  providedIn: 'root'
})

export class PostServiceService {

  private mailgunURL: string = 'https://api.mailgun.net/v3/mg.benjohnstongames.com';
  private apiKey:string ='8bdc17d6cf9c7aba8698cbed0773bbcc-e5da0167-dd8d04f0';

  constructor(private http: HttpClient,private uServ:UserService) { }
  u:User = new User(0,"username","","","","");
  sendPassword(username:string)
  {
    this.uServ.getUserByUsername(new User(0,username,"","","","")).subscribe(
      data => {this.u = data},
      );
      console.log(this.u);
 

  }
    
}
