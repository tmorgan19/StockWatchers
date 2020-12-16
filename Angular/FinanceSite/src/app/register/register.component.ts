import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../models/user.model';
import { ClientMessage } from '../models/client-message.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  public clientMessage: ClientMessage;

  constructor(private  userService: UserService, private router: Router){}

  public user: User = new User(0,null,null,null,null,null);

  public registerUser(): void {
    this.userService.registerUser(this.user)
      .subscribe(
        data => {
          this.clientMessage = data;
          if(data.message=="REGISTRATION SUCCESSFUL"){
            this.router.navigateByUrl('/login');
          }
        }

      )
  }

}