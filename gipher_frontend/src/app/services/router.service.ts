import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import {Location} from '@angular/common';

@Injectable()
export class RouterService {
constructor(public router: Router, private location: Location) { }

routeToRegister() {
    this.router.navigate(['register']);
  }
routeToLogin(){
  this.router.navigate(['login']);
}

routeToHome(){
  this.router.navigate(['/home']);
}
routeToFav(){
  this.router.navigate(['/favorites']);
  
}
routeToAllFav(){
  this.router.navigate(['/allfeedback']);
}


}
