import { catchError } from 'rxjs/operators';
import { User } from './../models/user.model';
import { UserService } from './user.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostServiceService {

  constructor(private http: HttpClient,private uServ:UserService) { }
  u:User;
  sendPassword(username:string)
  {
    this.uServ.getUserByUsername(new User(0,username,"","","","")).subscribe(
      data => {this.u = data},
      );

    const headers = new HttpHeaders({'enctype':'multipart/form-data',
    'Authorization':'Basic'+ btoa('api:8bdc17d6cf9c7aba8698cbed0773bbcc-e5da0167-dd8d04f0')
        });
  const formData = new FormData();
  formData.append('from','Maingun Sandbox <sandboxc5111414cbfc4b50b7e39c7634e035d5.mailgun.org>');
  formData.append('to',this.u.email);
  formData.append('subject',' Account Password For Stock Watch')
  formData.append('text','Your account password is: ' +this.u.password)

        this.http.post(
          'https://api.mailgun.net/v3/sandboxc5111414cbfc4b50b7e39c7634e035d5.mailgun.org/messages',formData, {headers}
        ).subscribe(res=> {console.log('res : ',res )},
                    err => {console.log('err: ',err)}
        )

  }
}
