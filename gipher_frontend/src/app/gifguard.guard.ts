import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticateService } from './services/authentication.service';
import { RouterService } from './services/router.service';

@Injectable({
  providedIn: 'root'
})
export class GifguardGuard implements CanActivate {

  
  constructor(private authservice: AuthenticateService,private router: RouterService){}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let mytoken=this.authservice.getbearerToken();
      if(mytoken==null)
      {
        this.router.routeToLogin();

        return false;
      }
      else{
      
        return true;
      }
  }
}
