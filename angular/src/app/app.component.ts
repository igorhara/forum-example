import { Component } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
  post:{[key:string]:any}={};
  postTitle:string="";

  constructor(private http:HttpClient){
     this.getPost();
  }

  getPost(){
     this.http.get("/api/post/all").subscribe((r:any[])=> {
       this.post = r[0];
       this.postTitle = r[0]['title'];
       console.log(r);
     });
  }
}
