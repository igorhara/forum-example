import {Component, OnInit} from "@angular/core";
import {ActivatedRoute, Params, Router} from "@angular/router";
import {PostService} from "../service/post.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Observable} from "rxjs/Observable";
import {Comment} from "../model/comment.model";

@Component({
  selector: 'app-comment-form',
  templateUrl: './comment-form.component.html',
  styleUrls: ['./comment-form.component.css']
})
export class CommentFormComponent implements OnInit {

  commentForm:FormGroup;
  postId:number;
  constructor(private fb:FormBuilder,private postService:PostService,private router:Router,private route:ActivatedRoute) {
    this.commentForm = this.fb.group({
      id:[],
      content:[,[Validators.required,Validators.maxLength(1000)]],
      postId:[]
    });
    this.route.params.subscribe((params:Params)=>{
      let temp = params['postId'];
      if(temp!=null){
        this.postId = +temp;
      }
    });
    this.route.data.subscribe(
      (data:{comment:Comment})=>{
        if(data && data.comment){
          this.commentForm.reset();
          this.commentForm.patchValue(data.comment);
        }

      });

  }

  ngOnInit() {

  }

  onSubmit(){
    var comment:Comment = this.commentForm.value;
    if(comment.postId==null) {
       comment.postId = this.postId;
    }
    var commentAction:Observable<Comment>;
    if(comment.id!=null ){
      if(comment.postId==null){
        this.onCancel();
        return;
      }
      commentAction = this.postService.updateComment(comment);
    }else{
      commentAction = this.postService.createComment(comment);

    }
    commentAction.subscribe(p=>this.router.navigate(['/post/view/',comment.postId]));
  }
  onCancel(){
    this.router.navigate(["/"]);
  }

}
