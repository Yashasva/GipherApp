import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import Gif from '../gif';

@Injectable({
  providedIn: 'root'
})
export class GifService {
  gifs = new BehaviorSubject<any>([]);

  constructor(private http: HttpClient) { }

  fetchGif()
  {
    return this.http.get('https://api.giphy.com/v1/gifs/trending?api_key=8l67fX89RqQpLGMZU74UsgJtUdn8LnXo')
    .subscribe((response : any)=>{
      this.gifs.next(response.data);
    });
  }
  
  searchGifs(gifName: string)
  {
    return this.http.get<any>(`https://api.giphy.com/v1/gifs/search?q=${gifName}&api_key=8l67fX89RqQpLGMZU74UsgJtUdn8LnXo`)
    .subscribe((response : any)=>{
      this.gifs.next(response.data);
    });
  }
  getGifs(){
    return this.gifs.asObservable();
  }
  
}
