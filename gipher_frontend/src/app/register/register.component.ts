import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { RouterService } from '../services/router.service';
import { User } from '../user';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    submitmessage:string;
    public user: User;
  
  constructor(private http: HttpClient, private route: RouterService) {
    this.user = new User();
    this.submitmessage=" ";
   }
   
  ngOnInit(): void {
  }

  RegisterHandler(){
    if(this.user.userPassword===this.user.confirmPass)
    {
console.log(this.user);
    // this.http.get<any>('http://api.giphy.com/v1/randomid?api_key=8l67fX89RqQpLGMZU74UsgJtUdn8LnXo')
    // .subscribe(response=>this.user.userId = response.data.random_id);
    this.http.post<any>('http://localhost:9901/api/v1/auth/register',this.user)
    .subscribe(response=>{console.log(response);
      sessionStorage.setItem("userId",this.user.userId);
      sessionStorage.setItem("userName",this.user.userName);
      
      sessionStorage.setItem("emailId",this.user.userId);
      sessionStorage.setItem("userPassword",this.user.userPassword);
      // sessionStorage.setItem(,);
      // sessionStorage.setItem(,);
    this.route.routeToLogin();
  },
    
    (err)=>{
      console.log(this.user);
      this.submitmessage=err.message;
      if(err.status===404){
        this.submitmessage = 'User already exist';
        this.route.routeToLogin();
      }

      alert(this.submitmessage);
  }
);
}
else{
  alert("Password and Confirm password needs to Match!!!!");
} 
  }
  
  
}
