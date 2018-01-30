import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {PostService} from "../service/post.service";
import {Post} from "../model/post.model";
import {ActivatedRoute, Router} from "@angular/router";
import {Observable} from "rxjs/Observable";

@Component({
  selector: 'app-post-form',
  templateUrl: './post-form.component.html',
  styleUrls: ['./post-form.component.css']
})
export class PostFormComponent implements OnInit {


  postForm:FormGroup;

  constructor(private fb:FormBuilder,private postService:PostService,private router:Router,private route:ActivatedRoute) {
    this.postForm = this.fb.group({
      id:[],
      title:[,Validators.required],
      category:[,Validators.required],
      content:[,[Validators.required,Validators.maxLength(1000)]]
    });
    this.route.data.subscribe(
      (data:{post:Post})=>{
        if(data && data.post){
          this.postForm.reset();
          this.postForm.patchValue(data.post);
        }

    });

  }

  ngOnInit() {

  }

  onSubmit(){
     var post:Post = this.postForm.value;
     var postAction:Observable<Post>;
     if(post.id!=null ){
       postAction = this.postService.updatePost(post);
     }else{
       postAction = this.postService.createPost(post);

     }
     postAction.subscribe(p=>this.router.navigate(['/']));


  }

}
