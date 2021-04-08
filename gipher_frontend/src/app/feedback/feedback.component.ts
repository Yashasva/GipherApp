import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { RouterService } from '../services/router.service';
import { Feedback } from '../feedback';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.css']
})
export class FeedbackComponent implements OnInit {
    flag: boolean=true;
   public feedback: Feedback;
   title = 'Star Rating';  
   submitMessage: string;
  constructor(private http: HttpClient, private route: RouterService, public router:RouterService) {
    this.feedback = new Feedback();
    this.submitMessage=" ";
   }   
  ngOnInit(): void {

   }
  
   FeedbackHandler(feedback: Feedback){
    console.log(sessionStorage.getItem('token'));
    this.feedback.userId=sessionStorage.getItem('userId');
    this.feedback.suggestion=feedback.suggestion;
    this.feedback.username=feedback.username;    
    this.feedback.rating=feedback.rating;
    this.feedback.feedback=feedback.feedback;

      this.http.post<any>('http://localhost:9910/api/v1/add',
    
      this.feedback,
      {
        headers: new HttpHeaders().set('Authorization',`Bearer ${sessionStorage.getItem('token')}`)
      }
      )
      .subscribe(response=>
        {
          console.log(response);
          alert("FeedBack has been submitted");
          this.route.routeToAllFav();
        },
        (err)=>{
          this.submitMessage=err.message;
          if(err.status===409){
            this.submitMessage = 'Feedback Already submitted';
          }
          alert(this.submitMessage);
          this.route.routeToAllFav();
        }
        );
  }

}
