import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../service/post.service";
import {Post} from "../model/post.model";
import {Router} from "@angular/router";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {


  postForm:FormGroup;

  constructor(private fb:FormBuilder,private postService:PostService,private router:Router) { }

  ngOnInit() {
    this.postForm = this.fb.group({
      id:[],
      title:[,Validators.required],
      category:[,Validators.required],
      content:[,[Validators.required,Validators.maxLength(1000)]]
    });
  }

  onSubmit(){
     var post:Post = this.postForm.value;

     this.postService.createPost(post).subscribe(p=>this.router.navigate(['/']));

  }

}
