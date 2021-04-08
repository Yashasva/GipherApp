import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import {  Subscription } from 'rxjs';
import Gif from '../gif';
import { GifService } from '../services/gif.service';
import { RouterService } from '../services/router.service';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  public gif:Gif;
  opened: boolean = false;
  breakpoint: number = 0;
  gifs: Array<any> = [];
 
  q :any;
  subscription: Subscription = new Subscription;
  submitMessage: any;
  constructor(private gifservice: GifService, private http: HttpClient, public router:RouterService) { 
    this.gif= new Gif();
  }
  ngOnInit(): void {
    if(window.innerWidth <= 400){
      this.breakpoint = 1;
    }
    else if(window.innerWidth<=800){
      this.breakpoint = 2;
    }
    else if(window.innerWidth<=1050){
      this.breakpoint = 3;
    }
    else{
      this.breakpoint = 4;
    }
    this.gifservice.fetchGif();
    this.subscription = this.gifservice.getGifs()
    .subscribe((response: any)=> {
      this.gifs=response;
      //  this.totallenght=this.gifs.length;
      console.log(response);
    });
  }
  @HostListener('window:resize', ['$event'])
  onResize(event: any){
    if(event.target.innerWidth <= 400){
      this.breakpoint = 1;
    }
    else if(event.target.innerWidth<=800){
      this.breakpoint = 2;
    }
    else if(event.target.innerWidth<=1050){
      this.breakpoint = 3;
    }
    else{
      this.breakpoint = 4;
    }
  }
  ngOnDestroy() {
    this.subscription.unsubscribe();
    window.addEventListener("keyup", disableF5);
     window.addEventListener("keydown", disableF5);
   
    function disableF5(e: { which: any; keyCode: any; preventDefault: () => void; }) {
       if ((e.which || e.keyCode) == 116) e.preventDefault(); 
    };
  }
  clickHandler(gif1: any){
    this.gif.gifId=gif1.id;
    this.gif.urlToImage=gif1.images.downsized.url;
    this.gif.title=gif1.title;
  if(sessionStorage.getItem('isAuthorized')==='true')
  {
    this.http.post<any>(`http://localhost:9903/api/v1/add/${sessionStorage.getItem('userId')}`,
    this.gif,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('token')}`)
    }
    )
    .subscribe(response=>{
      // alert("GIF added to Favorites");
      console.log("GIF added to Favorites");
  },
  (err) =>{
    this.submitMessage=err.message;
      if(err.status===409){
        this.submitMessage = 'GIF Already Added';
      }
      else {
        this.submitMessage='Http Error: Authentication Server Not connected';
      }
    // alert(this.submitMessage);
    console.log(this.submitMessage);
  }
    );
}
else{
  this.router.routeToLogin();
}
}
colorchange(signal: { isActive: boolean; }) {
  signal.isActive = !signal.isActive;
}
}