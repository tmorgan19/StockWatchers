import { ClientMessage } from './../models/client-message.model';
import { UserService } from './../services/user.service';
import { Component, OnInit } from '@angular/core';
import { User } from '../models/user.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  // depending on how currentUser is stored in session/local storage, this can be changed to that
  // below line for testing
  public currentUser: User = new User('jsmith','','','','')

  // store data pulled from backend in this
  public userInfo: User = new User('','','','','')

  // Message to user
  public clientMessage: ClientMessage = new ClientMessage('')

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.getUserData()
  }

  public getUserData(): void {
    this.userService.getUserByUsername(this.currentUser).subscribe(
      data => this.userInfo = data
      // error => this.ClientMessage.message = 'SOMETHING WENT WRONG'
    )
  }

  public updateUser(): void {
    this.userService.updateUser(this.userInfo).subscribe(
      data => this.clientMessage = data
    )
    
  }
}
