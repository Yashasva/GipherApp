import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import Gif from '../gif';
import { GifService } from '../services/gif.service';
import { RouterService } from '../services/router.service';
import { Subscription } from 'rxjs/internal/Subscription';
import { Feedback } from '../feedback';

@Component({
  selector: 'app-allfeedback',
  templateUrl: './allfeedback.component.html',
  styleUrls: ['./allfeedback.component.css']
})
export class AllfeedbackComponent implements OnInit {
  public gif:Feedback;
  gifs: Array<any> = [];
  rating: Array<number> = [0,0,0,0,0,0];
  subscription: Subscription = new Subscription;
  
  constructor(private gifservice: GifService, private http: HttpClient,  public router:RouterService) { 
    this.gif=new Feedback();
    }

  ngOnInit(): void {
    this.http.get<any>(`http://localhost:9910/api/v1`,
    {

      headers: new HttpHeaders().set('Authorization', `Bearer ${sessionStorage.getItem('token')}`)
    })
    .subscribe((response: any)=> { 
      this.gifs=response;
     
      this.gifs.forEach((item)=>{
        this.rating[item.rating] = this.rating[item.rating] + 1;
      })
    
    });  
   }
 
}
