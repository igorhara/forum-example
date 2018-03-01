import { Component, OnInit } from '@angular/core';
import {PostService} from "../service/post.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Post} from "../model/post.model";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrls: ['./post-detail.component.css']
})
export class PostDetailComponent implements OnInit {


  post:Post;
  constructor(private postService:PostService,private router:Router,private route:ActivatedRoute,private authService:AuthService) {
    this.route.data.subscribe(
      (data:{post:Post})=>{
        if(data && data.post){
          this.post = data.post;
        }

      });

  }

  ngOnInit() {
  }

  canCreate(){
    return this.authService.isLogged();
  }

  createComment(){
    this.router.navigate(["/comment/create",this.post.id]);
  }

}
