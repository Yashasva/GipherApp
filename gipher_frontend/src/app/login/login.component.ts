import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { AuthenticateService } from '../services/authentication.service';
import { RouterService } from '../services/router.service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public user: User;
  isAuthenticated: boolean;
  token: string;
  submitMessage: string;

  constructor(private http: HttpClient, public route: RouterService, public authservice: AuthenticateService) {
    this.user = new User();
    this.isAuthenticated = false;
    this.token='' ;
    this.submitMessage='';

   }

  ngOnInit(): void {
  }

  clickHandler(){
    console.log(this.user);
    this.http.post<any>('https://stormy-sands-73847.herokuapp.com/api/v1/auth/login', this.user)
    .subscribe(response=>{ 
    console.log(response.token);
    sessionStorage.setItem('token',response.token);
    sessionStorage.setItem('isAuthorized','true');
    console.log(response);
    sessionStorage.setItem("userId",this.user.userId);
    
    sessionStorage.setItem("emailId",this.user.userId);
    sessionStorage.setItem("userPassword",this.user.userPassword);
  
  },
  (err)=>{
      this.submitMessage = 'Email ID or Password Incorrect';
    alert(this.submitMessage);
  }

  );
     console.log(sessionStorage.getItem('token'));
    //  console.log(sessionStorage.)
  //  sessionStorage.setItem('username', this.user.userId);
    this.route.routeToHome();

  }
}

