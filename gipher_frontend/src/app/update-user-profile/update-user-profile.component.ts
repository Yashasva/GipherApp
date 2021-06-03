import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSidenav } from '@angular/material/sidenav';
import { LoginService } from '../services/login.service';
import { RouterService } from '../services/router.service';
import { User } from '../user';
import {FormControl, Validators} from '@angular/forms';

@Component({
  selector: 'app-update-user-profile',
  templateUrl: './update-user-profile.component.html',
  styleUrls: ['./update-user-profile.component.css']
})
export class UpdateUserProfileComponent implements OnInit {

  public user: User;
  isAuthenticated: boolean;
  token: string;
  submitMessage: string;

  constructor(private http: HttpClient,  public route: RouterService,public login: LoginService) { 
    this.user = new User();
    this.isAuthenticated = false;
    this.token='' ;
    this.submitMessage='';

   }

  ngOnInit(): void {
    console.log(this.user.userId);
    this.user.userId=sessionStorage.getItem('userId');
    this.http.get<any>(`http://localhost:9901/api/v1/auth/${this.user.userId}`)
    .subscribe(response=>{   
    this.user.userId=response.userId;
    this.user.emailId=response.emailId;
    this.user.phone=response.phone;
    this.user.userPassword=response.userPassword;
    this.user.confirmPass=response.userPassword;
    this.user.userName=response.userName;
  });
}

  update(){
    if(sessionStorage.getItem('userPassword')===this.user.confirmPass)
    { 
    this.http.put<any>(`http://localhost:9901/api/v1/auth/${this.user.userId}`, this.user)
    .subscribe(response=>{ 
      console.log(response.token);
      alert("User Profile successfully Updated!!");

    sessionStorage.setItem('token',response.token);
    sessionStorage.setItem('isAuthorized','true'); 
    },
    (err)=>{
      this.submitMessage = err.message;

      alert(this.submitMessage);
    });
    }
    else{
      alert("Password & Confirm Password should Match!!!!")
    }
    //  console.log(sessionStorage.getItem('token'));
    //  console.log(sessionStorage.)
      sessionStorage.setItem('userId', this.user.userId);
      this.route.routeToHome();

  }

}
