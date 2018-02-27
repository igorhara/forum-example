import {Component, Input, OnInit} from '@angular/core';
import {Comment} from "../../model/comment.model";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-comment-item',
  templateUrl: './comment-item.component.html',
  styleUrls: ['./comment-item.component.css']
})
export class CommentItemComponent implements OnInit {

  @Input() comment:Comment;
  constructor(private auth:AuthService) {

  }

  ngOnInit() {
  }


  canEdit(){
    return this.auth.isCurrentUser(this.comment.owner);
  }

}
