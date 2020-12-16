import { PostServiceService } from './../services/post-service.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-reset',
  templateUrl: './reset.component.html',
  styleUrls: ['./reset.component.css']
})
export class ResetComponent implements OnInit {

  constructor(private post:PostServiceService) { }

  username:string;
  ngOnInit(): void {
  }

  sendReset()
  {
    console.log(this.username);
    this.post.sendPassword(this.username);
    
   

  }

  submitUser(value:string)
  {
    this.username =value;
  }

}
