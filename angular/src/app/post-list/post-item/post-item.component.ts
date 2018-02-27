import {Component, Input, OnInit} from '@angular/core';
import {Post} from "../../model/post.model";
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-post-item',
  templateUrl: './post-item.component.html',
  styleUrls: ['./post-item.component.css']
})
export class PostItemComponent implements OnInit {

  @Input() post:Post;
  @Input() canNavigate:boolean = true;
  constructor(private auth:AuthService) { }

  ngOnInit() {
  }


  canEdit(){
    return this.auth.isCurrentUser(this.post.owner);
  }

}
