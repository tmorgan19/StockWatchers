import { ClientMessage } from './../models/client-message.model';
import { User } from './../models/user.model';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService : UserService, private router: Router) { }

  ngOnInit(): void {
  }

  public clientMessage: ClientMessage;

  public user: User = new User(0,'','','','','');

  public loginUser(): void {
    this.userService.loginUser(this.user)
    .subscribe(
      data => {
        if(data.message=="LOGIN SUCCESSFUL"){
          this.router.navigateByUrl('/home');
          let activeUser = this.userService.getUserByUsername(this.user);
          sessionStorage.setItem('activeUsername', this.user.username);
        }else{
          this.router.navigateByUrl('/login');
          this.clientMessage = new ClientMessage(data.message)
        }
      }
      
      )
  }

}
