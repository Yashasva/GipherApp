import { Injectable } from '@angular/core';
// import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import 'rxjs/Rx';
import { User } from '../user';

@Injectable({
  providedIn: 'root'
})
export class AuthenticateService {

  // constructor() { }

  url :string;
  constructor(private httpclient : HttpClient) { 

    this.url='https://stormy-sands-73847.herokuapp.com/api/v1/auth/login';
  }

  
  setbearerToken(tok: string)
  {
    sessionStorage.setItem("token",tok);
  }

  getbearerToken()
  {
    return sessionStorage.getItem("token");
  }


}
