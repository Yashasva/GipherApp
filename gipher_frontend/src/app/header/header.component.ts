import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSidenav } from '@angular/material/sidenav';
import { LoginService } from '../services/login.service';
import { RouterService } from '../services/router.service';
import { User } from '../user';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public user: User;
  
  constructor(private http: HttpClient,  public route: RouterService,public login: LoginService) { 
    this.user = new User();
    
  }
  ngOnInit(): void {
    this.user.userId=sessionStorage.getItem('userId');
  }
  clickfunc(){
    sessionStorage.clear();
    this.route.routeToHome();
  }
  clickHandler(){
    console.log(sessionStorage.getItem('token'));
    this.http.post('http://localhost:9910/api/v1/add',{
      "userId": "priyanka"
    },{
      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('token')}`)
    })
    .toPromise();
  }
  routeToFav(){
    this.route.routeToFav();
  }
 
}
