import {ActivatedRouteSnapshot, Resolve, Router, RouterStateSnapshot} from "@angular/router";
import {Post} from "../model/post.model";
import {Injectable} from "@angular/core";
import {PostService} from "../service/post.service";
import {Observable} from "rxjs/Observable";
import "rxjs/add/operator/catch";
import "rxjs/add/observable/throw";
import {Comment} from "../model/comment.model";
@Injectable()
export class CommentEditResolve implements Resolve<Comment>{


  constructor(private postService:PostService, private router:Router){}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Comment> | Promise<Comment> | Comment {
    let id:number = +route.params['id'];


    return this.postService.loadComment(id).catch(error=>{
      this.router.navigate([this.router.url]);
      return Observable.throw(error);
    });
  }

}
