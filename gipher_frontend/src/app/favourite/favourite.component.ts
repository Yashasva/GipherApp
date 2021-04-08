import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/internal/Subscription';
import Gif from '../gif';
import { GifService } from '../services/gif.service';
import { RouterService } from '../services/router.service';
import { Location } from '@angular/common';
import { Router } from '@angular/router';
@Component({
  selector: 'app-favourite',
  templateUrl: './favourite.component.html',
  styleUrls: ['./favourite.component.css']
})
export class FavouriteComponent implements OnInit {
  public gif:Gif;
  submitMessage: string;
  gifs: Array<any> = [];
  subscription: Subscription = new Subscription;
  constructor(private gifservice: GifService, private http: HttpClient, 
    public router:RouterService,private location: Location, private route: Router) { 
  this.gif=new Gif();
  this.submitMessage='';
  }
  ngOnInit(): void {
    this.http.get<any>(`http://localhost:9903/api/v1/${sessionStorage.getItem('userId')}`,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('token')}`)
    })
    .subscribe((response: any)=> {
      this.gifs=response;
      // console.log(response);
    });
   }
  delfavHandler(gif: any){
    // console.log(sessionStorage.getItem('token'));
    this.gif.gifId=gif.gifId;
     const username= sessionStorage.getItem('userId');
    this.http.delete<any>(`http://localhost:9903/api/v1/${username}/${this.gif.gifId}`
      ,
      {
        headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('token')}`)
      }
      )
    .subscribe(response=>{
},
(err) =>{
  this.submitMessage=err.message;
    if(err.status===404){
      this.submitMessage = 'GIF Not Found';
    }
    else {
      this.submitMessage='HttpError: FavGIF Server not Connected !!';
    }
alert(this.submitMessage);
}
  );
   let index: number=this.gifs.findIndex(n=>n.gifId==this.gif.gifId);
  this.gifs.splice(index,1);
  } 
}