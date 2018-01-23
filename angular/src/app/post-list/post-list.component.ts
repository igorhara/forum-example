import { Component, OnInit } from '@angular/core';
import {Post} from "../model/post.model";
import {PostService} from "../service/post.service";

@Component({
  selector: 'app-post-list',
  templateUrl: './post-list.component.html',
  styleUrls: ['./post-list.component.css']
})
export class PostListComponent implements OnInit {


  posts:Post[]=[];
  constructor(private postService:PostService) { }

  ngOnInit() {
     this.postService.getAllPosts().subscribe(p=>this.posts=p);
  }

}
