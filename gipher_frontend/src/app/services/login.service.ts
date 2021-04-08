import { Injectable } from '@angular/core';
@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor() { }
  isLogin(): boolean{
    if(sessionStorage.getItem('isAuthorized') === 'true')
      return true;
    return false;
  }
  isLogout(): void{
    sessionStorage.removeItem('isAuthorized');
  }
}