import { Component, OnInit } from '@angular/core';
import { GifService } from '../services/gif.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  constructor(private gifservice: GifService) { }

  ngOnInit(): void {
  }

  search(searchTerm: string){
    if(searchTerm !== ''){
      this.gifservice.searchGifs(searchTerm);
      console.log(searchTerm);
    }

  }

}
