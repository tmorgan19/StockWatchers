import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from './../models/user.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  constructor(private  userService: UserService){}

  public user: User = new User(0,'','','','','');

  public registerUser(): void {
    this.userService.registerUser(this.user)
      .subscribe()
  }

}